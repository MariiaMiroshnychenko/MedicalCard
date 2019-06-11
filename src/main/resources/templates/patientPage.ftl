<#import "parts/patient/common.ftl" as p>
<#import "parts/form-action.ftl" as fa>
<@p.page>
Please, choose your doctor
<#--<@fa.actionPost "/patientPage" "Choose attending doctor">-->
<#--<select title="doctorFIO" name="doctorFIO" multiple>-->
<#--<#list doctors as doctor>-->
<#--<option value="${doctor.surname}_${doctor.name}_${doctor.patronymic}">${doctor.surname} ${doctor.name} ${doctor.patronymic}</option>-->
<#--</#list>-->
<#--</select>-->
<#--</@fa.actionPost>-->
<a href="/appointment">To make appointment</a>
<link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css"/>
<link rel="stylesheet" href="http://bootstraptema.ru/plugins/font-awesome/4-4-0/font-awesome.min.css"/>
<script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>

<br><br><br>

<style>
    body {
        background: url(http://bootstraptema.ru/images/bg/bg-1.png)
    }

    #main {
        background-color: #f2f2f2;
        padding: 20px;
        -webkit-border-radius: 4px;
        -moz-border-radius: 4px;
        -ms-border-radius: 4px;
        -o-border-radius: 4px;
        border-radius: 4px;
        border-bottom: 4px solid #ddd;
    }

    #real-estates-detail #author img {
        -webkit-border-radius: 100%;
        -moz-border-radius: 100%;
        -ms-border-radius: 100%;
        -o-border-radius: 100%;
        border-radius: 100%;
        border: 5px solid #ecf0f1;
        margin-bottom: 10px;
    }

    #real-estates-detail .sosmed-author i.fa {
        width: 30px;
        height: 30px;
        border: 2px solid #bdc3c7;
        color: #bdc3c7;
        padding-top: 6px;
        margin-top: 10px;
    }

    .panel-default .panel-heading {
        background-color: #fff;
    }

    #real-estates-detail .slides li img {
        height: 450px;
    }
</style>

<div class="container">
    <div id="main">
        <div class="row" id="real-estates-detail">
            <div class="col-lg-4 col-md-4 col-xs-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <header class="panel-title">
                            <div class="text-center">
                                <strong>Пацієнт</strong>
                            </div>
                        </header>
                    </div>
                    <div class="panel-body">
                        <div class="text-center" id="author">
                        <#--<img src="${patientPhoto}" width="300" height="300">-->
                            <h3>${patientSurname} ${patientName} ${patientPatronymic}</h3>

                        <#--<form action="/doctorPage/changePhoto" method="post">-->
                        <#--<input type="file" name="photo">-->
                        <#--&lt;#&ndash;<button class="panel-default" title="Change photo">Change photo</button>&ndash;&gt;-->
                        <#--</form>-->
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8 col-md-8 col-xs-12">
                <div class="panel">
                    <div class="panel-body">
                        <ul id="myTab" class="nav nav-pills">
                            <li class="active"><a href="#detail" data-toggle="tab">About patient</a></li>
                            <li><a href="#confidentiality" data-toggle="tab">Confidentiality</a></li>
                            <li><a href="#medicalCard" data-toggle="tab">MedicalCard</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <hr>
                            <div class="tab-pane fade active in" id="detail">
                                <table class="table table-th-block">
                                    <tbody>
                                    <tr>
                                        <td class="active">Birth date:</td>
                                        <td>${patientBirthDate}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Phone number:</td>
                                        <td>${patientPhone}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Email:</td>
                                        <td>${patientEmail}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Attending doctor:</td>
                                        <td><a target="_blank"
                                               href="/doctorPageForPatient?attendingDoctorSurname=${attendingDoctorSurname}&attendingDoctorName=${attendingDoctorName}&attendingDoctorPatronymic=${attendingDoctorPatronymic}">${attendingDoctorSurname} ${attendingDoctorName} ${attendingDoctorPatronymic}</a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="confidentiality">
                                <form role="form">
                                <#--todo login changing-->
                                    <div class="form-group">
                                        <label>You can change your login</label>
                                    <#--<@fa.actionPost "/patientPage/changeLogin" "Change Login">-->
                                    <#--<input type="text" class="form-control rounded"-->
                                    <#--placeholder="Input new login" name="newLogin">-->
                                    <#--</@fa.actionPost>-->
                                    </div>
                                    <div class="form-group">
                                    <#--<#if passwordError>-->
                                    <#--<div class="alert alert-danger" role="alert">Passwords do not match!</div>-->
                                    <#--</#if>-->
                                        <label>You can change your password</label>
                                                <@fa.actionPost "/patientPage/changePassword" "Change Password">
                                            <input type="password" class="form-control rounded"
                                                   placeholder="Input new password" name="newPassword">
                                            <input type="password" class="form-control rounded"
                                                   placeholder="Repeat new password" name="repeatedPassword">
                                                </@fa.actionPost>
                                    </div>
                                </form>
                            </div>
                            <div class="tab-pane fade" id="medicalCard">
                                <label>Medical card num: ${medCardId}</label>
                                <div class="col-lg-8 col-md-8 col-xs-12">
                                    <div class="panel">
                                        <div class="panel-body">
                                            <ul id="myTab" class="nav nav-pills">
                                                <li class="active"><a href="#visits" data-toggle="tab">Visits</a></li>
                                                <li><a href="#examResults" data-toggle="tab">Exam results</a></li>
                                                <#--<li><a href="#discharges" data-toggle="tab">Discharges</a></li>-->
                                            </ul>
                                            <div id="myTabContent" class="tab-content">
                                                <hr>
                                                <div class="tab-pane fade active in" id="visits">
                                                    <table class="table table-th-block">
                                                        <thead>
                                                        <tr>
                                                            <th>Visit date</th>
                                                            <th>Referral num</th>
                                                            <th>Doctor speciality</th>
                                                            <th>Diagnosis</th>
                                                            <th>Appointment</th>
                                                            <th>Doctor full name</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                                <#list patientVisits as pVisit>
                                                                    <td>${pVisit.visitDate}</td>
                                                                    <td>${pVisit.idReferralToDoctor.id}</td>
                                                                    <td>${pVisit.doctorId.speciality}</td>
                                                                    <td>${pVisit.diagnosis}</td>
                                                                    <td>${pVisit.appointment}</td>
                                                                    <td>${pVisit.doctorId.person.surname} ${pVisit.doctorId.person.name} ${pVisit.doctorId.person.patronymic}</td>
                                                                     </tr>
                                                                </#list>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <div id="myTabContent" class="tab-content">
                                                <hr>
                                                <div class="tab-pane fade active in" id="examResults">
                                                    <table class="table table-th-block">
                                                        <thead>
                                                        <tr>
                                                            <th>Referral num</th>
                                                            <th>Result</th>
                                                            <th>Date</th>
                                                            <th>Doctor full name</th>
                                                            <th>Doctor speciality</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                                <#list referralResults as refResult>
                                                                   <td>${refResult.id}</td>
                                                                   <td>${refResult.resultText}</td>
                                                                   <td>${refResult.resDate}</td>
                                                                   <td>${refResult.respDoctorId.person.surname}
                                                                       ${refResult.respDoctorId.person.name}
                                                                       ${refResult.respDoctorId.person.patronymic}</td>
                                                                    <td>${refResult.respDoctorId.speciality}</td>
                                                                     </tr>
                                                                </#list>
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
            </div>
        </div>
    </div><!-- /.container -->
</@p.page>

