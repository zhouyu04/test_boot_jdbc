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

    <title>军师很帅</title>
</head>
<body>


<div style="margin-left:20px;margin-top: 30px">
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
</body>
<script type="text/javascript">

    function formatted(cellValue, options, rowObject) {
        if (cellValue == 2) {
            var formatted = "在途中";
            return formatted;
        } else if (cellValue == 3){
            var formatted = "签收";
            return formatted;
        } else if (cellValue == 4){
            var formatted = "问题件";
            return formatted;
        } else {
            var formatted = "暂无信息";
            return formatted;
        }
    }

    $(document).ready(function () {

        $("#jqGrid").jqGrid({
            url: '/express/getData',
            mtype: "GET",
            styleUI: 'Bootstrap',
            datatype: "json",
            colModel: [
                {label: '快递号', name: 'expressCode', width: 200},
                {label: '状态', name: 'status', formatter:formatted,width: 150},
                {label: '物流信息', name: 'trace', width: 1200}
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