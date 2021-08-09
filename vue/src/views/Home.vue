 <!--<template>
 <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <div id = "nav">
    <router-link v-bind:to="{name:'addRecipe'}">Add Recipe</router-link> &nbsp;|&nbsp;
    <router-link v-bind:to="{name:'addIngredient'}">Add Ingredient</router-link> &nbsp;|&nbsp;
    <router-link v-bind:to="{name:'recipeList'}">View my recipes</router-link> &nbsp;|&nbsp;
    <router-link v-bind:to="{name:'createMealPlan'}">Add Meal Plan</router-link> &nbsp;|&nbsp;
    <router-link v-bind:to="{name:'mealPlans'}">View Meal Plans</router-link> &nbsp;|&nbsp;
    <router-link v-bind:to="{name:'createmeal'}">Add Meal</router-link> &nbsp;|&nbsp;
    <router-link v-bind:to="{name:'meals'}">View Meals</router-link> &nbsp;|&nbsp;



    </div>
  </div>
</template>-->
<template>
  <body>
    <div class="flex-container-main">
      <div class="flex-item bordered flex-container-sub">
        <div class="divitems title-name"><h1>Ingredients</h1></div>
        <div class="divitems"><add-ingredient></add-ingredient></div>
      </div>
      <div class="flex-item bordered flex-container-sub">
        <div class="divitems title-name"><h1>Create a Recipe</h1></div>
        <div class="divitems"><add-recipe></add-recipe></div>
        <div class="button-div">
          <router-link v-bind:to="{ name: 'recipeList' }"
            ><button class="view-button">View my recipes</button></router-link
          >
        </div>
      </div>
      <div class="flex-item bordered flex-container-sub">
        <div class="divitems title-name"><h1>Create a Meal</h1></div>
        <div class="divitems"><create-meal></create-meal></div>
        <div class="button-div">
          <router-link v-bind:to="{ name: 'meals' }"><button class="view-button"> Meals</button></router-link>
        </div>
      </div>
      <div class="flex-item bordered flex-container-sub">
        <div class="divitems title-name"><h1>Create a Meal Plan</h1></div>
        <div class="divitems"><create-meal-plan></create-meal-plan></div>
        <div class="button-div">
            <router-link v-bind:to="{ name: 'mealPlans' }"
            ><button class="view-button">View Meal Plans</button></router-link>
        </div>
      </div>
    </div>
  </body>
</template>

<script>
import AddIngredient from "../components/AddIngredient.vue";
import AddRecipe from "../components/AddRecipe.vue";
import CreateMeal from "../components/CreateMeal.vue";
import CreateMealPlan from "../components/CreateMealPlan.vue";

export default {
  name: "home",
  components: {
    AddIngredient,
    AddRecipe,
    CreateMeal,
    CreateMealPlan,
  },
};
</script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Barlow+Condensed:wght@100&display=swap');
</style>
<style>
#nav a {
  font-weight: bold;
}

.flex-container-main {
  display: flex;
  justify-content: space-around;
  
}
.flex-container-sub{
  display: flex;
  background-color: #FFF2E6;
  
}
.bordered {
  border: 4px solid #Fa6900;
  padding: 15px;
  border-radius: 10px;
}

.divitems {
  
  padding: 15px;
  align-items: flex-start;
  font-family: 'Barlow Condensed', sans-serif;
  font-weight: bold;
  margin: 0 auto 0 auto;
}

.flex-item {
  flex-direction: column;
  
}
.view-button{
  display:inline-block;
padding:0.5em 3em;
border:0.16em solid #F38630;
margin:0 0.3em 0.3em 0;
box-sizing: border-box;
text-decoration:none;
text-transform:uppercase;
font-family:'Roboto',sans-serif;
font-weight:400;
background-color:white;
text-align:center;
transition: all 0.15s;
color:black;

}
.view-button:hover{
  color:black;
border-color:black;
background-color: whitesmoke;
}
.button-div{

margin: 5px auto 0 auto;

}
.title-name{
  text-decoration: underline;
}
</style> 