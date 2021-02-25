<#import "parts/common.ftlh" as c>

<@c.page>
    <form method="POST" action="/register">
        <h2>register</h2>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" value="email"
                       class="form-control"
                       placeholder="User email"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User email:</label>
            <div class="col-sm-6">
                <input type="password" name="password" value="password"
                       class="form-control"
                       placeholder="User Password"/>
            </div>
        </div>
        <br/>

        <button class="btn btn-primary" type="submit">
            Register
       </button>
    </form>
</@c.page>