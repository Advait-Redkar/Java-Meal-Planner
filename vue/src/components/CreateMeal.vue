<template>
    <div id="add">
    <div>
      <form v-on:submit.prevent>
        Meal Name:
        <input
          type="text"
          class="form-control"
          v-model="newMeal.mealName"
        />
        Type of Meal:
        <textarea class="form-control" v-model="newMeal.mealType" />

        <multiselect
          v-model="newMeal.recipeList"
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
        <div class="submit-btn">
        <button type="submit" class="btn" v-on:click="createMeal">
          Add Meal 
        </button>
        </div>
      </form>
    </div>
  </div>


</template>

<script>
import recipeService from "@/services/RecipeService";
import Multiselect from "vue-multiselect";
import mealService from "@/services/MealService";

export default {
    name: "create-meal",
    components: {Multiselect},
    data() {
        return {
            newMeal: {
                mealName: "",
                mealType: "",
                recipeList: [],
            },
            recipeList: [],
        };
    },
    methods: {
    createMeal() {
      mealService
        .addMeal(this.newMeal)
        .then((response) => {
          if (response.status === 200) {
            alert("Meal Succesfully Added");
            this.$router.push({ name: "home" });
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg =
              "Error adding meal. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsg =
              "Error adding meal. Server could not be reached.";
          } else {
            this.errorMsg =
              "Error adding meal. Request could not be created.";
          }
        });
    },
     toggleUnSelectRecipe(recipeId) {
      this.newMeal.recipes = this.newMeal.recipeList.filter((recipe) => {
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

<style>

</style>