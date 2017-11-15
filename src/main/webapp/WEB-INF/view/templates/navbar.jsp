	 <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/home">Home</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Show Rescuers<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/rescuer/all">All</a></li>
                <li><a href="/rescuer/available">Available</a></li>
                <li><a href="/rescuer/oncall">On Call</a></li>
                <li><a href="/rescuer/busy">Busy</a></li>
                <li><a href="/rescuer/retired">Retired</a></li>
              </ul>
            </li>
            <li><a href="/rescuer/add">Add Rescuer</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Show Operations<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/operation/all">All</a></li>
                <li><a href="/operation/current">Current</a></li>
                <li><a href="/operation/past">Past</a></li>
              </ul>
            </li>
            <li><a href="/operation/create">Create Operation</a></li>
          </ul>
        </div>
      </div>
    </nav>