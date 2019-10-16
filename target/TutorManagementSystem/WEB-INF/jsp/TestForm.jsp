<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tiutor System Application</title>
</head>
<body>
<center>
    <h1>Tiutor System Management</h1>
    <h2>
        <a href="new">Add New Test</a>
        &nbsp;&nbsp;&nbsp;
        <a href="list">List All Tests</a>

    </h2>
</center>
<div align="center">
    <c:if test="${test != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${test == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${test != null}">
                            Edit test
                        </c:if>
                        <c:if test="${test == null}">
                            Add New Test
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${test != null}">
                    <input type="hidden" name="id" value="<c:out value='${test.id}' />"/>
                </c:if>
                <tr>
                    <th>Title:</th>
                    <td>
                        <input type="text" name="title" size="45"
                               value="<c:out value='${test.title}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Description:</th>
                    <td>
                        <input type="text" name="description" size="45"
                               value="<c:out value='${test.description}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Subject:</th>
                    <td>
                        <input type="text" name="subjectId" size="5"
                               value="<c:out value='${test.subjectId}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Author:</th>
                    <td>
                        <input type="text" name="authorId" size="5"
                               value="<c:out value='${test.authorId}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Add"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>