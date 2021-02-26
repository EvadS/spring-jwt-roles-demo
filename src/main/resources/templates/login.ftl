<#import "parts/common.ftlh" as c>

<@c.page>
    <p>password per each : <b>123456</b></p>
    <p>the current users </p>
    <lu>
        <li>user@mail.com</li>
        <li>super-admin@mail.com</li>
        <li>admin@mail.com</li>
    </lu>
    <hr/>

    <form method="POST" action="/login?redirect=${RequestParameters.redirect!}">
        <h2>Log in</h2>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User email:</label>
            <div class="col-sm-6">
                <input type="email" name="username" value="user@mail.com"
                       class="form-control"
                       placeholder="User name"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User email:</label>
            <div class="col-sm-6">
                <input type="password" name="password" value="123456"
                       class="form-control"
                       placeholder="User Password"/>
            </div>
        </div>
        <br/>

        <button class="btn btn-primary" type="submit">
            Log In
        </button>
    </form>
</@c.page>