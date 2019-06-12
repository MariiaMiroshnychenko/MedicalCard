<#macro actionPost path b_value>
    <form action="${path}" method="post">
        <#nested>
        <table align="center">
            <tr>
                <td><input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" class="form-control button btn-success" style="width: 230px" value="${b_value}"/></td>
            </tr>
        </table>
    </form>
</#macro>

<#macro actionGet path b_value>
    <form action="${path}" method="get">
        <#nested>
        <table align="center">
            <tr>
                <td><input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" class="form-control button btn-success" style="width: 230px" value="${b_value}"/></td>
            </tr>
        </table>
    </form>
</#macro>