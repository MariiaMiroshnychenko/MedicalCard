<#macro actionPost path b_value>
    <form action="${path}" method="post">
        <#nested>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div><input type="submit" value="${b_value}"/></div>
    </form>
</#macro>

<#macro actionGet path b_value>
    <form action="${path}" method="get">
        <#nested>
        <div><input type="submit" value="${b_value}"/></div>
    </form>
</#macro>