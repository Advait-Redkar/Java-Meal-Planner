<template>
    <div id="add">
    <div>
      <div v-if="validationFailMsg.length > 0"> <!--DIY Validation Step 4 Add a div that shows the error if there is one. You should style this yourself-->
        Error: {{validationFailMsg}}
      </div>
      <form>
        Meal Name:
        <input
          type="text"
          class="form-control"
          v-model="newMeal.mealName"
          required
        />
        Type of Meal:
        <!-- <textarea class="form-control" v-model="newMeal.mealType" /> -->
        <select v-model="newMeal.mealType" required>
          <option value=1>Breakfast</option>
          <option value=2>Lunch</option>
          <option value=3>Dinner</option>
          <option value=4>Snack</option>
        </select>
        <div class="multiselect">
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
        </div>
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
            validationFailMsg: "",
        };
    },
    methods: {
    checkValidation() {
      this.validationFailMsg = "";
      if (this.newMeal.mealName.length === 0) {
        this.validationFailMsg += "Meal name is required\n";
      }
      if (this.newMeal.mealType.length === 0) {
        this.validationFailMsg += "Meal type is required\n";
      }
      if (this.newMeal.recipeList.length === 0) {
        this.validationFailMsg += "Recipe list is required\n";
      }
      return this.validationFailMsg.length ===0;
    },
    createMeal() {
      if (this.checkValidation()){
      mealService
        .addMeal(this.newMeal)
        .then((response) => {
          if (response.status === 200) {
            alert("Meal Succesfully Added");
            this.$router.push({ name: "home" });
            this.newMeal = {};
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
      }
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
.multiselect{
  margin: 10px 0 0 0;
}

</style>