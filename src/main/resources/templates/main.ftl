<#import "parts/common.ftl" as p>
<#import "parts/form-action.ftl" as fa>
<@p.page>
<h2>Hello, User!</h2>
<div><@fa.actionPost "/logout" "Sign out"/></div>
<div>
    <@fa.actionGet "/main" "Search">
        <label>Surname <input type="text" name="surname"></label>
    </@fa.actionGet>
    <@fa.actionGet "/main" "Search By Role">
        <label>Role <input type="text" name="role"></label>
    </@fa.actionGet>
    <@fa.actionGet "/main" "Search By Speciality">
        <label>Speciality <input type="text" name="speciality"></label>
    </@fa.actionGet>
    <@fa.actionGet "/main" "Search By Doctor ID">
        <label>Patient By DoctorId <input type="text" name="doctorId"></label>
    </@fa.actionGet>
</div>
<#list users as user>
    <div>
        <b>${user.login}</b>
        <b>${user.password}</b>
        <#--<b>${user.user}</b>-->
    </div>
<#else>
No users
</#list>
</@p.page>

<#--<br/>Your roleId is ${userRole}-->