<#import "parts/common-general.ftl" as p>

<@p.page>
<form action="/registration" method="post">
    <table align="center">
        <tr>
            <td>Surname : </td>
            <td><input type="text" class="form-control" name="surname"/></td>
        </tr>
        <tr>
            <td>Name : </td>
            <td><input type="text" class="form-control" name="name"/></td>
        </tr>
    <tr>
        <td>Patronymic : </td>
        <td><input type="text" class="form-control" name="patronymic"/></td>
    </tr>
        <tr>
            <td>Birth date : </td>
            <td><input type="date" class="form-control" name="birthDate"/></td>
        </tr>
        <tr>
            <td>Phone number : </td>
            <td><input type="text" class="form-control" name="phone"/></td>
        </tr>
        <tr>
            <td>Email : </td>
            <td><input type="email" class="form-control" name="email"/></td>
        </tr>
        <tr>
            <td>Enter your Login : </td>
            <td><input type="text" class="form-control" name="login"/></td>
        </tr>
        <tr>
            <td>Enter your Password : </td>
            <td><input type="password" class="form-control" name="password"/></td>
        </tr>
        <tr>
            <td>Your registration code : </td>
            <td><input type="text" class="form-control" name="code"/></td>
        </tr>
        <tr>
            <td> If you`re medical employee,<br>
                please, choose your specialty</td>
            <td>
                <select title="speciality" name="speciality">
                    <option value="Лікар-акушер-гінеколог">Лікар-акушер-гінеколог</option>
                    <option value="Лікар-гастроентеролог">Лікар-гастроентеролог</option>
                    <option value="Лікар-дієтолог">Лікар-дієтолог</option>
                    <option value="Лікар-ендокринолог">Лікар-ендокринолог</option>
                    <option value="Лікар загальної практики - сімейний лікар">Лікар загальної практики - сімейний лікар</option>
                    <option value="Лікар-кардіолог">Лікар-кардіолог</option>
                    <option value="Лікар-лаборант">Лікар-лаборант</option>
                    <option value="Лікар-невропатолог">Лікар-невропатолог</option>
                    <option value="Лікар-нефролог">Лікар-нефролог</option>
                    <option value="Лікар-нейрохірург">Лікар-нейрохірург</option>
                    <option value="Лікар-отоларинголог">Лікар-отоларинголог</option>
                    <option value="Лікар-офтальмолог">Лікар-офтальмолог</option>
                    <option value="Лікар-педіатр">Лікар-педіатр</option>
                    <option value="Лікар з променевої терапії">Лікар з променевої терапії</option>
                    <option value="Лікар-психіатр">Лікар-психіатр</option>
                    <option value="Лікар-рентгенолог">Лікар-рентгенолог</option>
                    <option value="Лікар-стоматолог">Лікар-стоматолог</option>
                    <option value="Лікар-терапевт">Лікар-терапевт</option>
                    <option value="Лікар-уролог">Лікар-уролог</option>
                    <option value="Лікар-фтизіатр">Лікар-фтизіатр</option>
                    <option value="Лікар-хірург">Лікар-хірург</option>
                    <option value="Медсестра">Медсестра</option>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="submit" class="form-control button btn-success" value="Sign Up" style="width: 230px"/></td>
        </tr>
    </table>
</form>
</@p.page>