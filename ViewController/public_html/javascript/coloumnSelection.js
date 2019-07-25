function clientCellSelectionCall(event) {
    component = event.getSource();
    var columnName = component.getProperty("columnName");
    AdfCustomEvent.queue(component, "cellSelection", 
    {
        payload : component, column : columnName
    },
true);
}