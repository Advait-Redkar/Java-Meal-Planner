<template>
  <body>
    <div id="app">
      <div class="container">
        <div class="box">
          <span><img src="@/assets/logo.png" class="logo-image" /></span>
        </div>
        <div class="box">
          <span><h1>Good Bytes</h1></span>
        </div>
        <div class="box">
          <span>
            <router-link v-bind:to="{ name: 'home' }"
              ><button class="nav-button home-button">Home</button></router-link
            >
            <router-link
              v-bind:to="{ name: 'logout' }"
              v-if="$store.state.token != ''"
              ><button class="nav-button">Logout</button></router-link
            ></span
          >
        </div>
      </div>
      <router-view />
    </div>
  </body>
</template>
<style >
@import url("https://fonts.googleapis.com/css2?family=Barlow+Condensed:wght@100&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Amatic+SC&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Amatic+SC:wght@700&display=swap");
</style>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.logo-image {
  width: 20%;
  height: 20%;
  justify-items: flex-start;
  flex-grow: 100%;
  margin: 5px 0 5px 5px;
}

.logout-home-buttons {
  display: flex;
  justify-items: flex-end;
  flex-grow: 100%;
  margin: 0 5px 0 0;
}

body {
  margin: 0;
  background-color: #ffbf80;
}
.nav-button {
  display: inline-block;
  padding: 0.5em 3em;
  border: 0.16em solid #f38630;
  margin: 0 0.3em 0.3em 0;
  box-sizing: border-box;
  text-decoration: none;
  text-transform: uppercase;
  font-family: "Roboto", sans-serif;
  font-weight: 400;
  background-color: #a7dbd8;
  text-align: center;
  transition: all 0.15s;
  color: black;
  /*margin-right: 20px*/
}
.nav-button:hover {
  color: black;
  border-color: #dddddd;
}

.container {
  display: flex;
  margin: 0 0 30px 0;
}

.box:first-child > span {
  margin-right: auto;
  display: inline-block;
  zoom: 8;
}

.box:last-child > span {
  margin-left: auto;
  display: inline-block;
}

.box {
  align-items: center;
  background-color: #a7dbd8;
  margin: 0 0 10px 0;
  height: 200px;
  flex: 1;
  display: flex;
  justify-content: center;
  font-size: 200%;
  font-family: "Amatic SC", cursive;
}
</style>
