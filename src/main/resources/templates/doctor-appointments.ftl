<#import "parts/doctor/general/common-doctor.ftl" as p>
<@p.page>
<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
    <a class="nav-link active" id="v-pills-home-tab" data-toggle="pill" href="#v-pills-visit" role="tab"
       aria-controls="v-pills-home" aria-selected="true">Visit</a>
    <a class="nav-link" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-referral" role="tab"
       aria-controls="v-pills-profile" aria-selected="false">Referral</a>
</div>
<div class="tab-content" id="v-pills-tabContent">
    <div class="tab-pane fade show active" id="v-pills-visit" role="tabpanel"
         aria-labelledby="v-pills-home-tab">
        <form method="post" action="/doctor-appointment">
            <table>
                <tbody>
                <tr>
                    <td>${enterValue}</td>
                    <td><input type="text" name="number"></td>
                </tr>
                <tr>
                    <td>Enter diagnosis</td>
                    <td><input type="text" name="diagnosis"></td>
                </tr>
                <tr>
                    <td>Choose appointment type</td>
                    <td><select name="appType">
                        <option value="Лікування">Лікування</option>
                        <option value="Спеціальне лікування">Спеціальне лікування</option>
                        <option value="Процедура">Процедура</option>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td>Enter appointments</td>
                    <td><input type="text" name="appointment"></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button style="width: 230px">Add visit</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>

    <div class="tab-pane fade" id="v-pills-referral" role="tabpanel"
         aria-labelledby="v-pills-profile-tab">
        <form method="post" action="doctor-appointment/referral">
            <table>
                <tbody>
                <tr>
                    <td>Patient id</td>
                    <td><input type="text" name="pId" value="${patientId}"></td>
                </tr>
                <tr>
                    <td>Referral type</td>
                    <td>
                        <select name="refType">
                            <option value="">--------------------------------------</option>
                            <option value="Направлення до лікаря за спеціальністю">
                                Направлення до лікаря за спеціальністю
                            </option>
                            <option value="Направлення на дослідження">Направлення на дослідження</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Act title</td>
                    <td><input type="text" name="actTitle"></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <button style="width: 350px">Issue referral</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</div>

<#--<ul class="nav nav-tabs">-->
<#--<li><a href="#all" data-toggle="tab">All appointments </a></li>-->
<#--<div id="myTabContent" class="tab-content">-->
<#--Search visit by date-->
<#--<form method="post" action="/doctorPage/doctor-appointment">-->
<#--<input type="date" title="date" name="date">-->
<#--<input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--<button>Search</button>-->
<#--</form>-->
<#--<hr>-->
<#--<div class="tab-pane fade active in" id="all">-->
<#--<table class="table table-th-block">-->
<#--<thead>-->
<#--<tr>-->
<#--<th>#</th>-->
<#--<th>Visit date</th>-->
<#--<th>Patient medical card #</th>-->
<#--<th>Patient full name</th>-->
<#--<th>Diagnosed</th>-->
<#--<th>Appointed</th>-->
<#--<th>Referred</th>-->
<#--</tr>-->
<#--</thead>-->
<#--<tbody>-->
<#--<tr>-->
<#--<#list doctorAppointments as da>-->
<#--<td>${da.counter}</td>-->
<#--<td>${da.patientVisit.visitDate}</td>-->
<#--<td>${da.patientVisit.patientId.medicalCard.mcId}</td>-->
<#--<td>${da.personalRegData.surname} ${da.personalRegData.name} ${da.personalRegData.patronymic}</td>-->
<#--<td>${da.patientVisit.diagnosis}</td>-->
<#--<td>${da.patientVisit.appointment}</td>-->
<#--<#list da.referrals as r>-->
<#--<td>${r.actTitle}</td>-->
<#--</#list>-->
<#--</tr>-->
<#--</#list>-->
<#--</tbody>-->
<#--</table>-->
<#--</div>-->
</@p.page>