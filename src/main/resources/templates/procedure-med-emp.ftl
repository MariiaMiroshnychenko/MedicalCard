<#import "parts/common-med-employee.ftl" as p>
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
                        <td></td>
                        <td>${tdl.appointment}</td>
                        <td> ${tdl.patientId.person.surname}
                            ${tdl.patientId.person.name}
                            ${tdl.patientId.person.patronymic}
                        </td>
                        <td>
                            <@fa.actionPost "/proceduresMedEmp" "Виконано">
                                <input type="hidden" name="visitId" value="${tdl.id}">
                                </@fa.actionPost>
                        </td>
                </tr>
                    </#list>
                </tbody>
            </table>
</@p.page>