<template>
  <div id="add">
    <div>
      <div v-if="validationFailMsg.length > 0"> <!--DIY Validation Step 4 Add a div that shows the error if there is one. You should style this yourself-->
        Error: {{validationFailMsg}}
      </div>
      <form>
        Recipe Name:
        <input
          type="text"
          class="form-control"
          v-model="newRecipe.recipeName"
          required
        />
        Recipe instructions:
        <textarea
          class="form-control"
          v-model="newRecipe.instructions"
          required
        ></textarea>
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
        ></multiselect>
        <div class="submit-btn">
          <button type="submit" class="btn" v-on:click.prevent="createRecipe"> <!--DIY Validation Step 5 Don't refresh automatically so add the .prevent -->
            Add Recipe
          </button>
        </div>
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
      validationFailMsg: "", //DIY Validation Step 1 - add a message to the data. If the length is 0, all is well. Otherwise it contains deets for the validation fail
    };
  },
  methods: {
    //DIY Validation Step 2 - add a method to do the validation. You can put everything here or just stuff that's not working. up to you
    //set the validationFailMsg based on checking all the fields. Return true at the end if length is == 0 indicating all is well or false if tehre's issues
    checkValidation() {
      this.validationFailMsg = "";
      if (this.newRecipe.recipeName.length === 0) {
        this.validationFailMsg += "Recipe name is required\n";
      }
      if (this.newRecipe.instructions.length === 0) {
        this.validationFailMsg += "Recipe instructions are required\n";
      }
      if (this.newRecipe.description.length === 0) {
        this.validationFailMsg += "Recipe description is required\n";
      }
      if (this.newRecipe.ingredients.length === 0) {
        this.validationFailMsg += "At least one ingredient is required";
      }
      return this.validationFailMsg.length ===0;
    },
    createRecipe() {
      if (this.checkValidation()) {//DIY Validation Step 3 - before you  call the service to call the back end do the validation an only make the call
      //if the validation returns true
        recipeService
          .addRecipe(this.newRecipe) 
          .then((response) => {
            if (response.status === 200) {
              alert("Recipe Succesfully Added");
              // this.$router.push({ name: "home" });
              this.newRecipe = {};
            }
          })
          .catch((error) => {
            if (error.response) {
              this.errorMsg =
                "Error adding recipe. Response received was '" +
                error.response.statusText +
                "'.";
            } else if (error.request) {
              this.errorMsg =
                "Error adding recipe. Server could not be reached.";
            } else {
              this.errorMsg =
                "Error adding card. Request could not be created.";
            }
          });
      }
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
  margin: 0 0 10px;
  display: inline-block;
  padding: 0.5em 3em;
  border: 0.16em solid #f38630;
  margin: 0 0.3em 0.3em 0;
  box-sizing: border-box;
  text-decoration: none;
  text-transform: uppercase;
  font-family: "Roboto", sans-serif;
  font-weight: 400;
  background-color: white;
  text-align: center;
  transition: all 0.15s;
  color: black;
}
.btn:hover {
  color: black;
  border-color: black;
  background-color: whitesmoke;
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
  border: #fa6900 2px solid;
  padding: 10px;
  border-radius: 10px;
}
.submit-btn {
  margin: 10px 0 0 0;
}
</style>
