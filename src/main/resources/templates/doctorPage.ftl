<#import "parts/common.ftl" as p>
<#import "parts/form-action.ftl" as fa>
<@p.page>
<h1>Hello, doctor!</h1>
Please, choose your specialty
<@fa.actionPost "/doctorPage" "Add speciality">
<select title="speciality" name="speciality" multiple>
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
</select>
</@fa.actionPost>
</@p.page>