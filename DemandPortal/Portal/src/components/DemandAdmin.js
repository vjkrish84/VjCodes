import React, { useState, useEffect } from "react";
import DatePicker from "react-datepicker";
import moment from "moment";
import "react-datepicker/dist/react-datepicker.css";
import CreatableSelect from "react-select/creatable";
import Table from "react-bootstrap/Table";
import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css";
import BootstrapTable from "react-bootstrap-table-next";
import filterFactory, { textFilter } from "react-bootstrap-table2-filter";
import cellEditFactory from "react-bootstrap-table2-editor";
import ToolkitProvider, {
  CSVExport,
  ColumnToggle,
  Search,
} from "react-bootstrap-table2-toolkit";
import {
  products,
  columns,
  searchfields,
  SERVER_URL,
  textfilterconst,
  formatterconst,
} from "../helpers/data";
import { Redirect } from "react-router-dom";
import { useSelector } from "react-redux";
import "./demand.css";
import paginationFactory from "react-bootstrap-table2-paginator";

const DemandAdmin = () => {
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
  const [textareavalue, setTextAreaValue] = useState("");
  const [commentareavalue, setCommentAreaValue] = useState("");
  const [startDate, setStartDate] = useState(null);
  const [etaDate, setEtaDate] = useState(null);
  const { user: currentUser } = useSelector((state) => state.auth);
  const { ToggleList } = ColumnToggle;

  useEffect(() => {
    var userfields = ["DemandRequestDate","ETA"];

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
    if (e != "") {
      console.log(x.options);
      f.label = e;
      f.value = e;
      x.options.push(f);
    }
    setIsLoading(false);
  };

  const defaultSorted = [
    {
      dataField: "Demandidentifier",
      order: "desc",
    },
  ];

  const searchDemand = () => {
    var inputvalues = {};
    var link =
      "mailto:MyReferrals@infinite.com?cc=healthcareadmin@infinite.com&subject=Referral&body=Please visit Referral Policy for details!";
    Object.keys(demandvalue).map((key) =>
      Object.keys(demandvalue[key]).map(
        (keyval) =>
          (inputvalues[camelCaseIt(keyval)] = demandvalue[key][keyval])
      )
    );
    //console.log(moment(startDate).format('DD-MM-YYYY'));
    inputvalues["demandRequestDate"]=startDate!==null ? moment(startDate).format('MM/DD/YYYY'):"";
    inputvalues["eta"]=etaDate!==null ? moment(etaDate).format('MM/DD/YYYY'):"";
    fetch([SERVER_URL.url] + "/api/demand/findDemandDataAdmin", {
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
                var invalues = {};

                invalues.text = x.name;
                invalues.dataField = camelCaseIt(x.name);
                if (x.name == "Demandidentifier") {
                  console.log("inside link" + formatterconst);
                  invalues.dataFormat = formatterconst;
                }

                invalues.filter = textfilterconst.filter;
                invalues.sort = true;
                // invalues.filter=textfilter()
                setTableColumns((tablecolumns) => [...tablecolumns, invalues]);
                console.log(tablecolumns);
                setColumLoad(false);
              })
            : setColumLoad(false);
            var invalues={};
            invalues.text = "DemandRequestDate";
            invalues.dataField = camelCaseIt("DemandRequestDate");
            invalues.filter = textfilterconst.filter;
            invalues.sort = true;
            columnload?setTableColumns((tablecolumns) => [...tablecolumns, invalues]):setColumLoad(false);
            var invalues={};
            invalues.text = "ETA";
            invalues.dataField = camelCaseIt("Eta");
            invalues.filter = textfilterconst.filter;
            invalues.sort = true;
            columnload?setTableColumns((tablecolumns) => [...tablecolumns, invalues]):setColumLoad(false);
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
          (inputvalues[camelCaseIt(keyval)] = demandvalue[key][keyval])
      )
    );
    //console.log("text"+document.getElementById('jobDescription').value);
    inputvalues["createdby"]=currentUser.username;
    inputvalues["createddate"]=new Date().toDateString;
    inputvalues["modifiedby"]=currentUser.username;
    inputvalues["modifieddate"]=new Date().toDateString;
    inputvalues["jobDescription"] = document.getElementById(
      "jobDescription"
    ).value;
    inputvalues["demandRequestDate"]=startDate!==null?moment(startDate).format('MM/DD/YYYY'):"";
    inputvalues["eta"]=etaDate!==null?moment(etaDate).format('MM/DD/YYYY'):"";
    inputvalues["comments"] = document.getElementById("comments").value;
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
        setMsg(result.message);
      })
      .catch((error) => {
        setMsg("Error Saving, Please Check the logs!");
        console.log("error", error);
      });
  };

  const editDemand = (row) => {
    console.log("inside save event" + JSON.stringify(row));
    row["modifiedby"]=currentUser.username;
    row["modifieddate"]=new Date().toDateString;
    fetch([SERVER_URL.url] + "/api/demand/createOrUpdateDemandData", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(row),
    })
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        setMsg(result.message);
      })
      .catch((error) => {
        setMsg("Error Saving, Please Check the logs!");
        console.log("error", error);
      });
  };

  const editDemand_expanded = (row) => {
    row["modifiedby"]=currentUser.username;
    row["modifieddate"]=new Date().toDateString;
    console.log("inside save event" + JSON.stringify(row));

    row["jobDescription"] =
      document.getElementById("jobDescription_edit") !== null
        ? document.getElementById("jobDescription_edit").value
        : "";
    row["comments"] =
      document.getElementById("comments_edit") !== null
        ? document.getElementById("comments_edit").value
        : "";
    fetch([SERVER_URL.url] + "/api/demand/createOrUpdateDemandData", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(row),
    })
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        setMsg(result.message);
      })
      .catch((error) => {
        setMsg("Error Saving, Please Check the logs!");
        console.log("error", error);
      });
  };

  const deleteMe = (row,done) => {
    setTimeout(() => {
      if (window.confirm("Do you want to delete this demand?")) {       
        deleteDemand(row);
      } 
    }, 0);
    return { async: true };
  }

  const deleteDemand = (row) => {    
    row["modifiedby"]=currentUser.username;
    row["modifieddate"]=new Date().toDateString;
    fetch([SERVER_URL.url] + "/api/demand/deleteData", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(row),
    })
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        setMsg("Demand Deleted Successfully ! ");
      })
      .catch((error) => {
        setMsg("Error Deleting, Please Check the logs!");
        console.log("error", error);
      });
  }

  if (!currentUser) {
    return <Redirect to="/login" />;
  }

  const handletextareachange = (e) => {
    console.log(e.target.value);
    setTextAreaValue(textareavalue, e.target.value);
  };

  const handlecommentchange = (e) => {
    console.log(e.target.value);
    setCommentAreaValue(commentareavalue, e.target.value);
  };
  const { SearchBar } = Search;

  const expandRow = {
    onlyOneExpanding: true,
    renderer: (row, rowIndex) => (
      <div>
        <div class="container-fluid row">
          <h5>&nbsp;&nbsp;&nbsp;Job Description:&nbsp;&nbsp;</h5>
          <textarea
            id="jobDescription_edit"
            rows={5}
            cols={100}
            defaultValue={row.jobDescription}
            onChange={(e) => handletextareachange(e)}
            onBlur={(e)=>editDemand_expanded(row)}/>
          &nbsp;&nbsp;&nbsp;
          <h5>&nbsp;&nbsp;&nbsp;Comments:&nbsp;&nbsp;</h5>
          <textarea
            id="comments_edit"
            rows={5}
            cols={100}
            defaultValue={row.comments}
            onChange={(e) => handlecommentchange(e)}
            onBlur={(e)=>editDemand_expanded(row)}/>       
            
        </div>
        <br/>
        &nbsp;&nbsp;&nbsp;<button style={{ "background-color":"red",color: "white", "font-size": "150%" }} onClick={(e)=>deleteMe(row)}><b>Delete Demand</b></button>
      </div>
    ),
    showExpandColumn: true,
    expandByColumnOnly: true,
  };

  function beforeSaveCell(oldValue, newValue, row, column, done) {
    setTimeout(() => {
      if (window.confirm("Do you want to accept this change?")) {
        done(true);
        console.log(row);
        editDemand(row);
      } else {
        done(false);
      }
    }, 0);
    return { async: true };
  }

  return (
    <div>
      <center>
        <h1>Demand Entry</h1>
        <i>
          This page should be updated by demand providers. If you don't find the
          values in the dropdown you can type the desired value and select
          Create option. Once you click that the value will be available for
          your use in the session.
        </i>
        <br />
        <b>
          <i style={{ color: "red", "font-size": "150%" }}>{msg}</i>
        </b>
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
                value={x.options.value}
              />
            </div>
          ))
        )}
      </div>
      &nbsp;&nbsp;&nbsp;
      <div class="container-fluid row">
      <h5> &nbsp;&nbsp;&nbsp;DemandRequestDate</h5><DatePicker  dateFormat="MM/dd/yyyy" selected={startDate}  isClearable onChange={date => setStartDate(date)} />
      <h5> &nbsp;&nbsp;&nbsp;ETA</h5><DatePicker selected={etaDate}   dateFormat="MM/dd/yyyy" isClearable onChange={date => setEtaDate(date)} />
      </div>
      &nbsp;&nbsp;&nbsp;
      <div class="container-fluid row">
        <h5>&nbsp;&nbsp;&nbsp;Job Description:&nbsp;&nbsp;</h5>
        <textarea
          id="jobDescription"
          rows={5}
          cols={100}
          onChange={(e) => handletextareachange(e)}
        />
        &nbsp;&nbsp;&nbsp;
        <h5>&nbsp;&nbsp;&nbsp;Comments:&nbsp;&nbsp;</h5>
        <textarea
          id="comments"
          rows={5}
          cols={100}
          onChange={(e) => handlecommentchange(e)}
        />
      </div>
      <br />
      <center>
        <button className="btn btn-success" onClick={() => saveDemand()}>
          Add Demand
        </button>
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
            columnToggle
            
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
                  cellEdit={cellEditFactory({ mode: "click", beforeSaveCell })}
                  wrapperClasses="table-responsive"
                  pagination={paginationFactory()}
                  expandRow={expandRow}
                  defaultSorted={defaultSorted}
                />
                <hr />
                <ToggleList {...props.columnToggleProps} />
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

export default DemandAdmin;
