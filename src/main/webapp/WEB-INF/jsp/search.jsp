<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <form class="form-inline" role="form" method="post" action="search">
                <div class="form-group">
                    <label for="name">Member Name:</label>
                    <input type="text" class="form-control" id="name" placeholder="Member Name" value="${info.name}">
                </div>

                <div class="form-group">
                    <label for="introducerName">Introducer:</label>
                    <input class="form-control" type="text" id="introducerName" placeholder="Introducer Name"
                           value="${info.introducerName}">
                </div>
                <div class="form-group">
                    <label for="acquisitionDate">Acquisition Date:</label>
                    <input type="text" class="form-control" id="acquisitionDate" readonly="readonly" style="width: 100px">
                </div>

                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    </div>
</div>

<script>
    $(function () {
        $('#acquisitionDate').datepicker();
    });
</script>