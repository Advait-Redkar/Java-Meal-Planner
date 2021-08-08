<template>
<body>
  <div id="app">
    <div id="nav">
      <nav class="nav-bar">
        <img src="@/assets/logo.png" class="logo-image"/>
        <div class="app-name">
        <h1>Good Bytes</h1>
        </div>
        <div class="logout-home-buttons">

              <router-link v-bind:to="{ name: 'home' }"><button class="nav-button">Home</button></router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''"><button class="nav-button">Logout</button></router-link>
        </div>
      </nav>

    </div>
    <router-view />
  </div>
</body>
</template>
<style >
@import url('https://fonts.googleapis.com/css2?family=Barlow+Condensed:wght@100&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Amatic+SC&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Amatic+SC:wght@700&display=swap');
</style>
<style>

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

}
.logo-image{
  width: 8%;
  height: 8%;
  justify-items: flex-start;
  flex-grow: 100%;
  margin: 5px 0 5px 5px;
}
.app-name{
  display: flex;
  justify-items: center;
  flex-grow: 100%;
  font-size: 200%;
/* font-family: 'Amatic SC', cursive; */
font-family: 'Amatic SC', cursive;
/* font-family: 'Barlow Condensed', sans-serif; */
}
.logout-home-buttons{
  display: flex;
  justify-items: flex-end;
  flex-grow: 100%;
  margin: 0 5px 0 0;
}

.nav-bar{
  display:flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin: 0 0 30px 0;
  
}
#nav{
  background-color: #A7DBD8;
  /* background-color: #69D2E7; */
  margin: 0 0 10px 0;
  border-radius: 6px;
}
body{
  margin: 0;
  background-color: #ffbf80 ;
}
.nav-button{
display:inline-block;
padding:0.5em 3em;
border:0.16em solid #F38630;
margin:0 0.3em 0.3em 0;
box-sizing: border-box;
text-decoration:none;
text-transform:uppercase;
font-family:'Roboto',sans-serif;
font-weight:400;
background-color:#A7DBD8;
text-align:center;
transition: all 0.15s;
color:black;

}
.nav-button:hover{
  
color:black;
border-color:#DDDDDD;

}
</style>
