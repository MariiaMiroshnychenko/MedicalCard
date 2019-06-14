<#import "parts/doctor/general/common-doctor.ftl" as p>
<#import "parts/form-action.ftl" as fa>

<@p.page>
            <table>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Patient full name</th>
                    <th>Appointment</th>
                    <th>Executed</th>
                </tr>
                </thead>
                <tbody>
                <tr>

                    <#list toDoList as tdl>
                        <td>  </td>
                        <td> ${tdl.appointment} </td>
                        <td> ${tdl.patientId.person.surname}
                            ${tdl.patientId.person.name}
                            ${tdl.patientId.person.patronymic}</td>
                        <td>
                            <@fa.actionPost "/procedures" "Виконано">
                                <input type="hidden" name="visitId" value="${tdl.id}">
                                </@fa.actionPost>
                        </td>
                </tr>
                    </#list>
                </tbody>
            </table>

<#--<form method="post" action="/doctor-appointment">-->
<#--<table>-->
<#--<tbody>-->
<#--<tr>-->
<#--<td>${enterValue}</td>-->
<#--<td><input type="text" name="number"></td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td>Enter diagnosis   </td>-->
<#--<td><input type="text" name="diagnosis"></td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td>Enter appointments   </td>-->
<#--<td><input type="text" name="appointment"></td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td></td>-->
<#--<td>-->
<#--<input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--<button style="width: 230px">Add visit</button>-->
<#--</td>-->
<#--</tr>-->
<#--</tbody>-->
<#--</table>-->
<#--</form>-->
<#--</div>-->

<#--<div class="tab-pane fade" id="v-pills-referral" role="tabpanel"-->
<#--aria-labelledby="v-pills-profile-tab">-->
<#--<form method="post" action="doctor-appointment/referral">-->
<#--<table>-->
<#--<tbody>-->
<#--<tr>-->
<#--<td>Patient id </td>-->
<#--<td><input type="text" name="pId" value="${patientId}"></td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td>Referral type   </td>-->
<#--<td>-->
<#--<select name="refType">-->
<#--<option value="">--------------------------------------</option>-->
<#--<option value="Направлення до лікаря за спеціальністю">-->
<#--Направлення до лікаря за спеціальністю-->
<#--</option>-->
<#--<option value="Направлення на дослідження">Направлення на дослідження</option>-->
<#--</select>-->
<#--</td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td>Act title   </td>-->
<#--<td><input type="text" name="actTitle"></td>-->
<#--</tr>-->
<#--<tr>-->
<#--<td></td>-->
<#--<td>-->
<#--<input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--<button style="width: 350px">Issue referral</button>-->
<#--</td>-->
<#--</tr>-->
<#--</tbody>-->
<#--</table>-->
<#--</form>-->
</@p.page>