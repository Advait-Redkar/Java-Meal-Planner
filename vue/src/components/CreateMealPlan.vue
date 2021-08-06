<template>
  <div id="add">
    <h1>Meal Plan</h1>
    <div>
      <h1>Create Meal Plan Page</h1>
      <form v-on:submit.prevent>
        Meal Plan Name:
        <input
          type="text"
          class="form-control"
          v-model="newMealPlan.mealplanName"
        />
        Type of Meal:
        <textarea class="form-control" v-model="newMealPlan.mealplanTime" />
        

        Day of Week:
        <input type="radio" id= 1  name="day_of_week" value= 1 v-model="newMealPlan.mealplanDay" />
        <label for= 1>Monday</label><br />
        <input type="radio" id= 2  name="day_of_week" value= 2 v-model="newMealPlan.mealplanDay" />
        <label for= 2>Tuesday</label><br />
        <input type="radio" id= 3  name="day_of_week" value= 3 v-model="newMealPlan.mealplanDay" />
        <label for= 3>Wednesday</label><br />
        <input type="radio" id= 4  name="day_of_week" value= 4 v-model="newMealPlan.mealplanDay" />
        <label for= 4>Thursday</label><br />
        <input type="radio" id= 5  name="day_of_week" value= 5 v-model="newMealPlan.mealplanDay" />
        <label for= 5>Friday</label><br />
        <input type="radio" id= 6  name="day_of_week" value= 6 v-model="newMealPlan.mealplanDay" />
        <label for= 6>Saturday</label><br />
        <input type="radio" id= 7  name="day_of_week" value= 7 v-model="newMealPlan.mealplanDay" />
        <label for= 7>Sunday</label><br />
        
        <multiselect
          v-model="newMealPlan.recipeList"
          :options="recipeList"
          label="recipeName"
          :multiple="true"
          :close-on-select="false"
          :clear-on-select="false"
          :preserve-search="true"
          placeholder="Select Your Recipe"
          track-by="recipeId"
          @remove="toggleUnSelectRecipe"
        ></multiselect>
        <button type="submit" class="btn" v-on:click="createMealPlan">
          Add Meal Plan
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import recipeService from "@/services/RecipeService";
import mealPlanService from "@/services/mealPlanService";
import Multiselect from "vue-multiselect";
export default {
  name: "create-meal-plan",
  components: { Multiselect },
  data() {
    return {
      newMealPlan: {
        mealplanName: "",
        mealplanTime: "",
        mealplanDay: null,
        recipeList: [],
      },
      recipeList: [],
    };
  },
  methods: {
    createMealPlan() {
      mealPlanService
        .addMealPlan(this.newMealPlan)
        .then((response) => {
          if (response.status === 200) {
            alert("Meal Plan Succesfully Added");
            this.$router.push({ name: "home" });
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg =
              "Error adding meal plan. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsg =
              "Error adding meal plan. Server could not be reached.";
          } else {
            this.errorMsg =
              "Error adding meal plan. Request could not be created.";
          }
        });
    },
    toggleUnSelectRecipe(recipeId) {
      this.newMealPlan.recipes = this.newMealPlan.recipeList.filter((recipe) => {
        return recipe.recipeId != recipeId;
      });
    },
  },
  computed: {
    options: () => this.recipeList,
  },
  created() {
    recipeService.listAllRecipes().then((response) => {
      this.recipeList = response.data;
      //this.isLoading = false;
    });
  },
};
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style></style>
