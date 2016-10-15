<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setHeader("Expires","0");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<% String path=request.getContextPath() + "/resources";%>
<% String pathHost=request.getContextPath();%>
<%-- <link rel="stylesheet" href="<%=path%>/core/css/bootstrap.min.css">
<script type="text/javascript" charset="utf-8" src="<%=path%>/core/js/bootstrap.min.js"/></script>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

<html lang="zh-tw">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>&#x62DB;&#x5FB7;&#x5EAB;&#x5B58;&#x7CFB;&#x7D71;</title>


</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">&#x62DB;&#x5FB7;&#x5EAB;&#x5B58;&#x7CFB;&#x7D71;</a>
		</div>
	</div>
</nav>

<h2></h2>
<%-- <div class="jumbotron">
	<div class="container">
		<h1>${title}</h1>
		 <p>
		 <c:set var="details" value="${companyList}"/>
		 <c:forEach begin="0" end="5" varStatus="status">
			<c:if test="${not empty details}">
				Hello ${details.ID}
			</c:if>

			<c:if test="${empty msg}">
				Welcome Welcome!
			</c:if>
			</c:forEach>
		</p>
		<p>
			<a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
		</p>
		
	</div>
</div>
--%>
<div id="companyListDiv" style="margin-top: 100px;">
<c:if test="${companyList != null}">
<div style="text-align:left;font-size:24px;">
&#x5BA2;&#x6236;&#x6E05;&#x55AE;
</div>
  <div style="height: 29px;">
            <table style="margin-top:10px;" class="table table-hover table-condensed table-striped table-bordered list">
            <thead>
              <tr class="parts-title">
                <th class="tdclass" style="width: 20%;">
              <div style="float: left;"></div>
            </th>
            <th class="tdclass" style="width: 40%;">
                    <div style="float: left;">&#x5BA2;&#x6236;&#x540D;&#x7A31;</div>
                </th>
                <th class="tdclass" style="width: 40%;">
                    <div style="float: left;">&#x8A73;&#x7D30;&#x8CC7;&#x6599;</div>
                </th>
              </tr>
            </thead>
            </table>
  </div>
          <div style="margin-top: 0px;">
          <c:if test="${companyList != null && fn:length(companyList) > 10}">
                <div style="height: 291px; overflow-y: scroll;">
                <table class="table table-hover table-condensed table-striped table-bordered list">
            <tbody>
              <c:forEach var="obj" items="${companyList}" varStatus="status">
                  <tr>
                  <td style="width: 20%">${obj.ID}</td>
                  <td style="width: 40%"><c:out value="${obj.companyName}"></c:out></td>
                  <td style="width: 37%"><a href="<%=pathHost%>/DannyStorageWebGAE/searchProductList?searchCompanyName=${obj.companyName}" >&#x7522;&#x54C1;&#x6E05;&#x55AE;</a></td>
                </tr>
              </c:forEach>
            </tbody>
            </table>
        </div>
        </c:if>
        <c:if test="${companyList != null && fn:length(companyList) <= 10}">
                <div>
                <table class="table table-hover table-condensed table-striped table-bordered list">
            <tbody>
              <c:forEach var="obj" items="${companyList}" varStatus="status">
                  <tr>
                  <td style="width: 20%"><c:out value="${obj.ID}"></c:out></td>
                  <td style="width: 40%"><c:out value="${obj.companyName}"></c:out></td>
                  <td style="width: 40%"><a href="<%=pathHost%>/DannyStorageWebGAE/searchProductList?searchCompanyName=${obj.companyName}" >&#x7522;&#x54C1;&#x6E05;&#x55AE;</a></td>
                </tr>
              </c:forEach>
            </tbody>
            </table>
        </div>
        </c:if>
          </div>
        </c:if>
 </div>
 
 
 <div id="productListDiv" style="margin-top: 50px;">
<c:if test="${productListByCompany != null}">
<div style="text-align:left;font-size:24px;">
&#x5BA2;&#x6236; : ${companyName}
<br>
&#x7522;&#x54C1;&#x6E05;&#x55AE;
</div>
  <div style="height: 29px;">
            <table style="margin-top:10px;" class="table table-hover table-condensed table-striped table-bordered list">
            <thead>
              <tr class="parts-title">
                <th class="tdclass" style="width: 10%;">
              		<div style="float: left;"></div>
	            </th>
	            <th class="tdclass" style="width: 20%;">
	                    <div style="float: left;">&#x7522;&#x54C1;&#x7DE8;&#x865F;</div>
	            </th>
                <th class="tdclass" style="width: 20%;">
                    <div style="float: left;">&#x7522;&#x54C1;&#x540D;&#x7A31;</div>
                </th>
                <th class="tdclass" style="width: 10%;">
                    <div style="float: left;">&#x9644;&#x8A3B;</div>
                </th>
                <th class="tdclass" style="width: 20%;">
                    <div style="float: left;">&#x5EAB;&#x5B58;&#x5730;&#x9EDE;</div>
                </th>               
                <th class="tdclass" style="width: 10%;">
                    <div style="float: left;">&#x5BA2;&#x6236;&#x7DE8;&#x865F;</div>
                </th>
                 <th class="tdclass" style="width: 10%;">
                    <div style="float: left;">&#x5EAB;&#x5B58;&#x6578;&#x91CF;</div>
                </th>
              </tr>
            </thead>
            </table>
          </div>
          <div style="margin-top: 0px;">
          <c:if test="${productListByCompany != null && fn:length(productListByCompany) > 10}">
                <div style="height: 291px; overflow-y: scroll;">
                <table class="table table-hover table-condensed table-striped table-bordered list">
            <tbody>
              <c:forEach var="obj" items="${productListByCompany}" varStatus="status">
                  <tr>
                  <td style="width: 10%">${status.index + 1}</td>
                  <td style="width: 20%"><c:out value="${obj.ID}"></c:out></td>
                  <td style="width: 20%"><a href="<%=pathHost%>/DannyStorageWebGAE/searchProductDetail?searchCompanyName=${companyName}&searchProductName=${obj.name}" >${obj.name}</a></td>
                  <td style="width: 10%"><c:out value="${obj.note}"></c:out></td>
                  <td style="width: 20%"><c:out value="${obj.location}"></c:out></td>
                  <td style="width: 10%"><c:out value="${obj.belingcompanyID}"></c:out></td>
                  <td style="width: 9%"></td>
                </tr>
              </c:forEach>
            </tbody>
            </table>
        </div>
        </c:if>
        <c:if test="${productListByCompany != null && fn:length(productListByCompany) <= 10}">
                <div>
                <table class="table table-hover table-condensed table-striped table-bordered list">
            <tbody>
              <c:forEach var="obj" items="${productListByCompany}" varStatus="status">
                <tr>
                  <td style="width: 10%">${status.index + 1}</td>
                  <td style="width: 20%"><c:out value="${obj.ID}"></c:out></td>
                  <td style="width: 20%"><a href="<%=pathHost%>/DannyStorageWebGAE/searchProductDetail?searchCompanyName=${companyName}&searchProductName=${obj.name}" >${obj.name}</a></td>
                  <td style="width: 10%"><c:out value="${obj.note}"></c:out></td>
                  <td style="width: 20%"><c:out value="${obj.location}"></c:out></td>
                  <td style="width: 10%"><c:out value="${obj.belingcompanyID}"></c:out></td>
                  <td style="width: 10%"></td>
                </tr>
              </c:forEach>
            </tbody>
            </table>
        </div>
        </c:if>
          </div>
        </c:if>
 </div>



<div>
	<hr>
	<footer>
		<p>&copy; DANNY Inc.</p>
	</footer>
</div>


</body>
</html>