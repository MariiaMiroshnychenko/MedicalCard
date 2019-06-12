<#import "parts/doctor/general/common-doctor.ftl" as p>
<@p.page>
<div class="container">
    <div id="main">
        <div class="row" id="real-estates-detail">
            <div class="col-lg-4 col-md-4 col-xs-12">
                <div class="panel panel-default">
                    <div class="col-lg-8 col-md-8 col-xs-12">
                        <div class="panel">
                            <div class="panel-body">
                                <ul id="myTab" class="nav nav-pills">
                                    <li><a href="#all" data-toggle="tab">All appointments </a></li>
                                    <li><a href="#begin" data-toggle="tab">Begin an appointment</a></li>
                                </ul>
                                <div id="myTabContent" class="tab-content">
                                        Search visit by date
                                            <#--<form method="post" action="/doctorPage/doctor-appointment">-->
                                                <#--<input type="date" title="date" name="date">-->
                                                <#--<input type="hidden" name="_csrf" value="${_csrf.token}">-->
                                                <#--<button>Search</button>-->
                                            <#--</form>-->
                                    <hr>
                                    <div class="tab-pane fade active in" id="all">
                                        <table class="table table-th-block">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Visit date</th>
                                                <th>Patient medical card #</th>
                                                <th>Patient full name</th>
                                                <th>Diagnosed</th>
                                                <th>Appointed</th>
                                                <th>Referred</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                            <#list doctorAppointments as da>
                                <td>${da.counter}</td>
                                <td>${da.patientVisit.visitDate}</td>
                                <td>${da.patientVisit.patientId.medicalCard.mcId}</td>
                                <td>${da.personalRegData.surname} ${da.personalRegData.name} ${da.personalRegData.patronymic}</td>
                                <td>${da.patientVisit.diagnosis}</td>
                                <td>${da.patientVisit.appointment}</td>
                                <#list da.referrals as r>
                                <td>${r.actTitle}</td>
                                </#list>
                            </tr>
                            </#list>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div id="myTabContent" class="tab-content">
                                        <hr>
                                        <div class="tab-pane fade active in" id="begin">
                                            <table class="table table-th-block">
                                                <tbody>
                                                <tr>
                                                    <td>
                                                        <#--<form method="post" action="/doctorPage/doctor-appointment">-->
                                                            <#--Enter referral number <input type="text" name="refNumber"><br/>-->
                                                            <#--Enter diagnosis <input type="text" name="diagnosis"><br/>-->
                                                            <#--Enter appointments <input type="text" name="appointment"><br/>-->
                                                            <#--Referral type-->
                                                            <#--<select name="refType">-->
                                                            <#--<option value="Направлення до лікаря за спеціальністю">Направлення до лікаря за спеціальністю</option>-->
                                                            <#--<option value="Направлення на дослідження">Направлення на дослідження</option>-->
                                                        <#--</select><br/>-->
                                                            <#--Act title <input type="text" name="actTitle"><br/>-->
                                                            <#--<input type="hidden" name="_csrf" value="${_csrf.token}">-->
                                                            <#--<button>Add visit</button>-->
                                                        <#--</form>-->
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</@p.page>