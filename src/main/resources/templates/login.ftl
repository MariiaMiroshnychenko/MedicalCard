<#import "parts/patient/common.ftl" as p>
<#import "parts/form-action.ftl" as fa>
<#import "parts/login.ftl" as l>
<@p.page>
Login page
<#if logout>
  <div class="alert alert-info" role="alert">You've been logged out</div>
</#if>
<#if error>
    <div class="alert alert-danger" role="alert">Incorrect input data!</div>
</#if>
    <@fa.actionPost "/" "Sign in">
        <@l.login "username"/>
    </@fa.actionPost>
<a href="/registration">Add new user</a>
</@p.page>