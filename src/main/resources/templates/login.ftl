<#import "parts/common-general.ftl" as p>
<#import "parts/form-action.ftl" as fa>
<#import "parts/login.ftl" as l>
<@p.page>
<#if logout>
  <div class="alert alert-info" role="alert">You've been logged out</div>
</#if>
<#if error>
    <div class="alert alert-danger" role="alert">Incorrect input data!</div>
</#if>
    <@fa.actionPost "/" "Sign in">
        <@l.login "username"/>
    </@fa.actionPost>

            <div class="card-footer">
                <div class="d-flex justify-content-center links">
                    Don't have an account? <a href="/registration"> Sign Up</a>
                </div>
            </div>
</@p.page>