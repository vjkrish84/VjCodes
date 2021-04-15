import React, { useState, useEffect } from "react";

import CreatableSelect from "react-select/creatable";
import Table from "react-bootstrap/Table";
import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css";
import BootstrapTable from "react-bootstrap-table-next";
import filterFactory, { textFilter } from "react-bootstrap-table2-filter";
import cellEditFactory from "react-bootstrap-table2-editor";
import ToolkitProvider, { CSVExport } from "react-bootstrap-table2-toolkit";
import {
  products,
  columns,
  searchfields,
  SERVER_URL,
  textfilterconst,
} from "../helpers/data";
import { SendMail } from "./SendMail";
import { Redirect } from "react-router-dom";
import { useSelector } from "react-redux";
import "./demand.css";

const DemandUser = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [isInitiaload, setLoad] = useState(true);
  const [optionsfield, setOptions] = useState([]);
  const [value, setValue] = useState([]);
  const [rowcounter, setCounter] = useState(true);
  const [demandvalueinit, setDemandValueInit] = useState(0);
  const [demandvalue, setDemandValue] = useState([]);
  const { ExportCSVButton } = CSVExport;
  const [tablecolumns, setTableColumns] = useState([]);
  const [tablevalues, setTableValues] = useState([]);
  const [columnload, setColumLoad] = useState(true);
  const [msg, setMsg] = useState("");
  const { user: currentUser } = useSelector((state) => state.auth);

  useEffect(() => {
    var userfields = ["Probability", "Priority", "SPOC", "Type", "ETA","ConduentHiringManager","RequestedBy","DemandRequestDate","Level","BillRate","Duration","ProfilesCount","Tocrid","SourceType","SourceName","Status"];

    fetch([SERVER_URL.url] + "/api/demand/find", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ active: "Y" }),
    })
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        var optval = [];
        result.map((x, i) => {
          if (userfields.indexOf(x.name) > -1) {
            console.log("Skipping due to restrictions");
          } else {
            console.log('i am here' + i + " " + x.name);
            optval.push(result[i]);
          }
        });
        setOptions(...optionsfield, optval);
        setLoad(false);
        console.log(optionsfield);
      })
      .catch((error) => console.log("error", error));
    //setIsLoading(true);
    console.log("isloading" + isLoading);
  }, []);

  const handleCreate = (x, e) => {
    var f = {};
    if (e !== "" && e !== null) {
      console.log(x.options);
      f.label = e;
      f.value = e;
      x.options.push(f);
    }
    setIsLoading(false);
  };

  const defaultSorted = [{
    dataField: 'Demandidentifier',
    order: 'desc'
  }];

  const searchDemand = () => {
    var inputvalues = {};
    var userfields = ["Probability", "Priority", "SPOC", "Type", "ETA","ConduentHiringManager","RequestedBy","DemandRequestDate","Level","BillRate","Duration","ProfilesCount","Tocrid","SourceType","SourceName","Status"];
    var link =
      "mailto:MyReferrals@infinite.com?cc=healthcareadmin@infinite.com&subject=Referral&body=Please visit Referral Policy for details!";
    Object.keys(demandvalue).map((key) =>
      Object.keys(demandvalue[key]).map(
        (keyval) =>
          (inputvalues[camelCaseIt(keyval)] = demandvalue[key][keyval])
      )
    );
    fetch([SERVER_URL.url] + "/api/demand/findDemandDataUser", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(inputvalues),
    })
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        {
          columnload
            ? [...optionsfield].map((x, i) => {
                if (userfields.indexOf(x.name) > -1) {
                  console.log("Skipping due to restrictions");
                } else {
                  var invalues = {};
                  invalues.text = x.name;
                  invalues.dataField = camelCaseIt(x.name);
                  invalues.filter = textfilterconst.filter;
                  invalues.sort=true;
                  // invalues.filter=textfilter()
                  setTableColumns((tablecolumns) => [
                    ...tablecolumns,
                    invalues,
                  ]);
                  console.log(tablecolumns);
                  setColumLoad(false);
                }
              })
            : setColumLoad(false);
          setTableValues((tablevalues) => (tablevalues, result));
          console.log(tablevalues);
        }
      })
      .catch((error) => console.log("error", error));
  };
  const camelCaseIt = (string) =>
    string.charAt(0).toLowerCase() + string.slice(1);

  const saveDemand = () => {
    //console.log("inside save event" + demandvalue[0].Vertical);
    var inputvalues = {};
    Object.keys(demandvalue).map((key) =>
      Object.keys(demandvalue[key]).map(
        (keyval) =>
          (inputvalues[keyval.toLowerCase()] = demandvalue[key][keyval])
      )
    );

    fetch([SERVER_URL.url] + "/api/demand/createOrUpdateDemandData", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(inputvalues),
    })
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        setMsg("Data Saved Succesfully!");
      })
      .catch((error) => {
        setMsg("Error Saving, Please Check the logs!");
        console.log("error", error);
      });
  };

  if (!currentUser) {
    return <Redirect to="/login" />;
  }

  const expandRow = {
    onlyOneExpanding: true,
    renderer: (row, rowIndex) => <div>{row.jobDescription}</div>,
    showExpandColumn: true,
    expandByColumnOnly: true
  };
  const mailto = (row) => {
    var demand =
      row.demandidentifier !== null && row.demandidentifier !== undefined
        ? row.demandidentifier + " "
        : "";
    var acc =
      row.account !== null && row.account !== undefined
        ? row.account + " "
        : "";
    var bu =
      row.businessUnit !== null && row.businessUnit !== undefined
        ? row.businessUnit + " "
        : "";
    var title =
      row.positionTitle !== null && row.positionTitle !== undefined
        ? row.positionTitle
        : "";
    var string =
      "mailto:MyReferrals@infinite.com?cc=Conduent-OM-PMO@infinite.com&subject=" +
      demand +
      acc +
      bu +
      title +
      "&body=Thank you for referring your friend / family member. Kindly review the job description before sharing. Please visit Referral Policy for details! Do not change the subject line !";
    // console.log(string);
    window.location = string;
  };
  const rowEvents = {
    onClick: (e, row, rowIndex) => {
      console.log(e);
      mailto(row);
    },
  };

  return (
    <div>
      <center>
        <h1>Demand Entry</h1>
        <i>Please click on any row to refer your friend / family for the position! </i>
        <br />
        <i>{msg}</i>
      </center>
      <br />

      <div class=" container-fluid row row-cols-4">
        {isInitiaload ? (
          <i>Loading...</i>
        ) : (
          [...optionsfield].map((x, i) => (
            <div class="col">
              <h5>{x.name}</h5>
              <CreatableSelect
                isClearable
                isDisabled={isLoading}
                isLoading={isLoading}
                onChange={(e) => {
                  setMsg("");
                  console.log(e);
                  console.log(demandvalue);
                  if (e != null) {
                    var demandv = { [x.name]: e.value };
                    demandvalue.push(demandv);
                  } else {
                    const demandv = demandvalue.filter(
                      (item) => item[x.name] == undefined
                    );
                    console.log(demandv);
                    setDemandValue(demandv);
                    console.log(demandvalue);
                  }
                  setDemandValueInit(1);
                  console.log(demandvalue);
                  setIsLoading(false);
                }}
                onCreateOption={(e) => {
                  setMsg("");
                  console.log("create called" + e);
                  handleCreate(x, e);
                  console.log(x.options);
                }}
                options={x.options}
                value={x.options!= undefined ? x.options.value:""}
              />
            </div>
          ))
        )}
      </div>
      <br />
      <center>
        &nbsp;&nbsp;&nbsp;
        <button
          className="btn btn-success"
          onClick={() => {
            setMsg("");
            searchDemand();
          }}
        >
          Search Demand
        </button>
      </center>
      <br />
      {tablecolumns.length > 0 ? (
        <div>
          <ToolkitProvider
            keyField="demandidentifier"
            data={tablevalues}
            columns={tablecolumns}
            exportCSV
          >
            {(props) => (
              <div>
                {/* &nbsp;&nbsp;&nbsp;
                <button type="submit" className="btn btn-success">
                  Update Demand
                </button> */}
                &nbsp;&nbsp;&nbsp;
                <ExportCSVButton
                  {...props.csvProps}
                  className="btn btn-success"
                >
                  Export CSV!!
                </ExportCSVButton>
                <hr />
                <BootstrapTable
                  {...props.baseProps}
                  filter={filterFactory()}
                  expandRow={expandRow}
                  rowEvents={rowEvents}
                  wrapperClasses="table-responsive"
                  defaultSorted={defaultSorted}
                />
              </div>
            )}
          </ToolkitProvider>
        </div>
      ) : (
        <div></div>
      )}
    </div>
  );
};

export default DemandUser;
