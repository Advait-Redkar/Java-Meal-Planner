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
    <div class="flex-container">
      <div class="flex-item bordered flex-container">
        <div class="divitems"><h1>Ingredients</h1></div>
        <div class="divitems"><add-ingredient></add-ingredient></div>
      </div>
      <div class="flex-item bordered flex-container">
        <div class="divitems"><h1>Ingredients</h1></div>
        <div class="divitems"><add-recipe></add-recipe></div>
        <div>
          <router-link v-bind:to="{ name: 'recipeList' }"
            >View my recipes</router-link
          >
        </div>
      </div>
      <div class="flex-item bordered flex-container">
        <div class="divitems"><h1>Ingredients</h1></div>
        <div class="divitems"><create-meal></create-meal></div>
        <div>
          <router-link v-bind:to="{ name: 'meals' }">View Meals</router-link>
        </div>
      </div>
      <div class="flex-item bordered flex-container">
        <div class="divitems"><h1>Ingredients</h1></div>
        <div class="divitems"><create-meal-plan></create-meal-plan></div>
        <div>
          <router-link v-bind:to="{ name: 'mealPlans' }"
            >View Meal Plans</router-link
          >
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
#nav a {
  font-weight: bold;
}

.flex-container {
  display: flex;
  justify-content: space-around;
}

.bordered {
  border: 3px solid black;
  padding: 15px;
  border-radius: 10px;
}

.divitems {
  border: 1px solid black;
  padding: 15px;
}

.flex-item {
  flex-direction: column;
}
</style> 