<template>
  <div id="add">
    <div>
      <form v-on:submit.prevent>
        Recipe Name:
        <input
          type="text"
          class="form-control"
          v-model="newRecipe.recipeName"
        />
        Recipe instructions:
        <textarea class="form-control" v-model="newRecipe.instructions" />
        Recipe Description:
        <textarea class="form-control" v-model="newRecipe.description" />
        <multiselect
          v-model="newRecipe.ingredients"
          :options="ingredients"
          label="ingredientName"
          :multiple="true"
          :close-on-select="false"
          :clear-on-select="false"
          :preserve-search="true"
          placeholder="Select Your Ingredient"
          track-by="ingredientId"
          @remove="toggleUnSelectIngredient"
        ></multiselect>
        <button type="submit" class="btn" v-on:click="createRecipe">
          Add Recipe
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import recipeService from "@/services/RecipeService";
import ingredientService from "@/services/IngredientService";
import Multiselect from "vue-multiselect";
export default {
  components: { Multiselect },
  name: "add-recipe",
  data() {
    return {
      newRecipe: {
        recipeName: "",
        instructions: "",
        description: "",
        ingredients: [],
      },
      ingredients: [],
    };
  },
  methods: {
    createRecipe() {
      recipeService
        .addRecipe(this.newRecipe)
        .then((response) => {
          if (response.status === 200) {
            alert("Recipe Succesfully Added");
            this.$router.push({ name: "home" });
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg =
              "Error adding recipe. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsg = "Error adding recipe. Server could not be reached.";
          } else {
            this.errorMsg = "Error adding card. Request could not be created.";
          }
        });
    },
    toggleUnSelectIngredient(ingredientId) {
      this.newRecipe.ingredients = this.newRecipe.ingredients.filter(
        (ingredient) => {
          return ingredient.ingredientId != ingredientId;
        }
      );
    },
  },
  computed: {
    options: () => this.ingredients,
  },
  created() {
    ingredientService.listIngredients().then((response) => {
      this.ingredients = response.data;
      //this.isLoading = false;
    });
  },
};
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>
<style>
.form-control {
  margin-bottom: 10px;
}
.btn {
  margin-bottom: 35px;
}

form {
  display: flex;
  flex-direction: column;
  margin: 10px;
}

#add {
  display: flex;
  justify-content: center;
}

form {
  border: black 3px solid;
  padding: 10px;
  border-radius: 10px;
}
</style>
