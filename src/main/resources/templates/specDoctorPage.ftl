<#import "parts/doctor/general/common-doctor.ftl" as p>
<#import "parts/form-action.ftl" as fa>
<@p.page>

<#--Please, choose your specialty-->
<#--<@fa.actionPost "/specDoctorPage" "Add speciality">-->
<#--<select title="speciality" name="speciality" multiple>-->
<#--<option value="Лікар-акушер-гінеколог">Лікар-акушер-гінеколог</option>-->
<#--<option value="Лікар-гастроентеролог">Лікар-гастроентеролог</option>-->
<#--<option value="Лікар-дієтолог">Лікар-дієтолог</option>-->
<#--<option value="Лікар-ендокринолог">Лікар-ендокринолог</option>-->
<#--<option value="Лікар загальної практики - сімейний лікар">Лікар загальної практики - сімейний лікар</option>-->
<#--<option value="Лікар-кардіолог">Лікар-кардіолог</option>-->
<#--<option value="Лікар-лаборант">Лікар-лаборант</option>-->
<#--<option value="Лікар-невропатолог">Лікар-невропатолог</option>-->
<#--<option value="Лікар-нефролог">Лікар-нефролог</option>-->
<#--<option value="Лікар-нейрохірург">Лікар-нейрохірург</option>-->
<#--<option value="Лікар-отоларинголог">Лікар-отоларинголог</option>-->
<#--<option value="Лікар-офтальмолог">Лікар-офтальмолог</option>-->
<#--<option value="Лікар-педіатр">Лікар-педіатр</option>-->
<#--<option value="Лікар з променевої терапії">Лікар з променевої терапії</option>-->
<#--<option value="Лікар-психіатр">Лікар-психіатр</option>-->
<#--<option value="Лікар-рентгенолог">Лікар-рентгенолог</option>-->
<#--<option value="Лікар-стоматолог">Лікар-стоматолог</option>-->
<#--<option value="Лікар-терапевт">Лікар-терапевт</option>-->
<#--<option value="Лікар-уролог">Лікар-уролог</option>-->
<#--<option value="Лікар-фтизіатр">Лікар-фтизіатр</option>-->
<#--<option value="Лікар-хірург">Лікар-хірург</option>-->
<#--</select>-->
<#--</@fa.actionPost>-->
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
                            <strong>${specDoctorSpeciality}</strong>
                            </div>
                        </header>
                    </div>
                    <div class="panel-body">
                        <div class="text-center" id="author">
                        <img src="${specDoctorPhoto}" width="300" height="300">
                            <h3>${specDoctorFullName}</h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8 col-md-8 col-xs-12">
                <div class="panel">
                    <div class="panel-body">
                        <ul id="myTab" class="nav nav-pills">
                            <li class="active"><a href="#detail" data-toggle="tab">About doctor</a></li>
                            <li><a href="#confidentiality" data-toggle="tab">Confidentiality</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <hr>
                            <div class="tab-pane fade active in" id="detail">
                                <table class="table table-th-block">
                                    <tbody>
                                    <tr>
                                        <td class="active">Birth date:</td>
                                        <td>${specDoctorBirthDate}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Phone number:</td>
                                        <td>${specDoctorPhoneNumber}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">Email:</td>
                                        <td>${specDoctorEmail}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="confidentiality">
                                <label>You can change your login</label>
                                    <@fa.actionPost "/doctorPage/changeLogin" "Change Login">
                                    <input type="text" class="form-control rounded"
                                           placeholder="Input new login" name="newLogin">
                                    </@fa.actionPost>
                                <label>You can change your password</label>
                                                <@fa.actionPost "/doctorPage/changePassword" "Change Password">
                                            <input type="password" class="form-control rounded"
                                                   placeholder="Input new password" name="newPassword">
                                            <input type="password" class="form-control rounded"
                                                   placeholder="Repeat new password" name="repeatedPassword">
                                                </@fa.actionPost>
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

