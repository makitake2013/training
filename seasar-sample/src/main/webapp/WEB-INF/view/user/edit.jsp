<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${f:url('/css/global.css')}"/>
</head>
<body>

<html:errors/>

<s:form>
        <html:hidden property="id" />
<table class="tablebg">
	<tr>
		<td> firstName </td>
		<td>
		    <html:text property="firstName" />
		</td>	
	</tr>
	<tr>
		<td> lastName </td>
		<td>
		    <html:text property="lastName" />
		</td>	
	</tr>

</table>

<input type="submit" name="update" value="UPDATE" />
</s:form>
<br/><br/>

<s:link href="/user/">list page</s:link>

</body>
</html>