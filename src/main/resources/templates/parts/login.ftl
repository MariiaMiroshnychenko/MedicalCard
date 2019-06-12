<#macro login l_name>
    <#nested>
<table align="center">
    <tbody>
    <tr>
        <td><input type="text" class="form-control"  name="${l_name}" placeholder="login"/></td>
    </tr>
    <tr>
        <td><input type="password" class="form-control" name="password" placeholder="password"/></td>
    </tr>
    </tbody>
</table>
    <#--<div><label> Login <input type="text" name="${l_name}"/> </label></div>-->
        <#--<div><label> Password <input type="password" name="password"/> </label></div>-->
</#macro>