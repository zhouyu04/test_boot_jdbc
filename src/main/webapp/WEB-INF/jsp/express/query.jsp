<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>

    <!-- The jQuery library is a prerequisite for all jqSuite products -->
    <script type="text/ecmascript"
            src="/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <!-- We support more than 40 localizations -->
    <script type="text/ecmascript"
            src="/plugins/jqGrid-master/js/i18n/grid.locale-cn.js"></script>
    <script type="text/javascript"
            src="/plugins/daterangepicker/moment.js"></script>
    <!-- This is the Javascript file of jqGrid -->
    <script type="text/ecmascript"
            src="/plugins/jqGrid-master/js/jquery.jqGrid.min.js"></script>
    <!-- This is the localization file of the grid controlling messages, labels, etc.
        <!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <!-- The link to the CSS that the grid needs -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="/plugins/jqGrid-master/css/ui.jqgrid-bootstrap.css"/>

    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <meta charset="utf-8"/>

    <title>jqGrid Loading Data - Million Rows from a REST service</title>
</head>
<body>

<div style="margin-left:20px;margin-top: 20px">

    <form name="Form2" action="/express/upload" method="post"  enctype="multipart/form-data">
        <input type="file"  name="file" value="请选择文件"><input type="submit" value="上传"/>
    </form>

</div>
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