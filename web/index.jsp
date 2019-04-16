<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 16.04.2019
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<div style="position: absolute; margin: auto; top: 0; right: 0; bottom: 0; left: 0; width: 205px; height: 180px; background-color: #f2f2f2;">
    <form method="get" action="load">
    <table>
        <tr>
            <th><h1 style="width:200px;">Hangman</h1></th>
        </tr>
        <tr>
            <td style="width:200px;">Select language:</td>
        </tr>
        <tr>
            <td>
                <select name="lang" style="width:200px;">
                    <option value="pol">Poland</option>
                    <option value="eng">English</option>
                </select>
            </td>
        </tr>
        <tr><td></td></tr>
        <tr><td></td></tr>
        <tr>
            <td>
                <input type="submit" value="Start" style="width:200px;"/>
            </td>
        </tr>
    </table>
    </form>
</div>
</body>
</html>
