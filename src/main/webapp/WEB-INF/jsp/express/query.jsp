<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="../../../css/trirand/ui.jqgrid-bootstrap.css"/>
    <script src="/js/jquery-2.2.3.min.js"></script>
    <script src="/js/jquery-ui.min.js"></script>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/colorpicker/bootstrap-colorpicker.js"></script>
    <script type="text/ecmascript" src="/js/i18n/grid.locale-en.js"></script>
    <script type="text/ecmascript" src="/js/jquery.jqGrid.js"></script>
    <script src="/bootstrap/bootstrap.min.js"></script>
    <title>jqGrid Loading Data - Million Rows from a REST service</title>
</head>
<body>
<div style="margin-left:20px;margin-top: 30px">
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {

        $("#jqGrid").jqGrid({
            url: 'http://trirand.com/blog/phpjqgrid/examples/jsonp/getjsonp.php?callback=?&qwery=longorders',
            mtype: "GET",
            styleUI: 'Bootstrap',
            datatype: "jsonp",
            colModel: [
                {label: 'OrderID', name: 'OrderID', key: true, width: 75},
                {label: 'Customer ID', name: 'CustomerID', width: 150},
                {label: 'Order Date', name: 'OrderDate', width: 150},
                {label: 'Freight', name: 'Freight', width: 150},
                {label: 'Ship Name', name: 'ShipName', width: 150}
            ],
            viewrecords: true,
            height: 600,
            rowNum: 20,
            pager: "#jqGridPager"
        });
    });

</script>
</head>
</html>