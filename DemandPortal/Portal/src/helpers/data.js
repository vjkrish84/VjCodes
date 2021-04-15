import filterFactory, { textFilter } from "react-bootstrap-table2-filter";
export const SERVER_URL={url:"http://localhost:8080"};
export const textfilterconst = { filter: textFilter()};
export const formatterconst =  "formatter: (cell, row, rowIndex, extraData) => (  <div>    <a href={link}> {row.value} </a>  </div>)";
export const products = [
    {
      id: 1,
      Vertical: "Conduent",
      Account: "Conduent",
      Project: "CTI",
      Role:"Azure Architect",
      Type:"FP",
      Priority:"1",
      Interview:"Yes",
      Probability:"100%",
      Skills: "SSIS",
      DemandSPOC:"ABC",
      Offshore:"3",
      Onshore:"0",
      Filled:"1",
      Aging:"10-20 days",
      ETA:"Feb-2021",
      Status:"Active"
    }
  ];


  export const columns = [
    {
      dataField: "id",
      text: "Demand ID"
    },
    {
      dataField: "Vertical",
      text: "Vertical",
      filter: textFilter()
    },
    {
      dataField: "Account",
      text: "Account",
      filter: textFilter()
    },
    
    {
      dataField: "Project",
      text: "Project",
      filter: textFilter()
    },
    {
      dataField: "Role",
      text: "Role"
    },
    {
      dataField: "Type",
      text: "Type"
    },
    {
      dataField: "Priority",
      text: "Priority"
    },
    {
      dataField: "Interview",
      text: "Interview"
    },
    {
      dataField: "Probability",
      text: "Probability"
    },
    {
      dataField: "Skills",
      text: "Skills"
    },
    {
      dataField: "DemandSPOC",
      text: "DemandSPOC",
      filter: textFilter()
    },
    {
      dataField: "Offshore",
      text: "Offshore"
    },
    {
      dataField: "Onshore",
      text: "Onshore"
    },
    {
      dataField: "Filled",
      text: "Filled"
    },
    {
      dataField: "Aging",
      text: "Aging"
    },
    {
      dataField: "ETA",
      text: "ETA"
    },
    {
      dataField: "Status",
      text: "Status",
      filter: textFilter()
    }
  ];

  export const searchfields=[
      {
          name: "verticals",
          label: "Verticals",
          options: [                
               {value:"Conduent",label:"Conduent"},
               {value:"Payer",label:"Payer"},
               {value:"Provider",label:"Provider"},
               {value:"Other",label:"Other"}
            ],
            placeholder: "Type to add new Vertical"
      },
      {
        name: "account",
        label: "Account",
        options: [                
             {value:"Conduent",label:"Conduent"},
             {value:"Molina",label:"Molina"}
          ],
          placeholder: "Type to add new Account"
    }
  ];