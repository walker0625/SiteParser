<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>SiteParser</title>
</head>
	<body>
	    <div class="console">
	        <div class="input">
	            <div>
	                <h3>[입력]</h3>
	            </div>
	            <div>
	                URL <input id="url" type="text" placeholder = "https://www.google.com">
	            </div>
	            <div>
	                Type
	                <select id="type" name="type">
	                    <option value="onlyText" selected="selected">HTML 태그 제외</option>
	                    <option value="all">Text 전체</option>
	                </select>
	            </div>
	            <div>
	                출력단위묶음 <input id="size" type="number">
	            </div>
	            <div>
	                <input type="button" value="출력" onclick="parse()">
	            </div>
	        </div>
	        <div class="output">
	            <div>
	                <h3>[출력]</h3>
	            </div>
	            <div>
	                몫 : <span id="quotient"></span>
	            </div>
	            <div>
	                나머지 : <span id="remainder"></span>
	            </div>
	        </div>
	    </div>

	    <script>
	        function parse(){
	            
	        	var input = {
	                url: $("#url").val(),
	                type : $("#type").val(),
	                size : $("#size").val()
	            };
	        	
	            $.ajax({
	                url: "/parsing-output",
	                method: "post",
	                data: input,
	                success: function(output) {
	                    $("#quotient").text(output.quotient);
	                    $("#remainder").text(output.remainder);
	                },
	                error : function(err) {
	                    console.log(err);
	                }
	            });
	            
	        }
	    </script>
   	</body>
</html>