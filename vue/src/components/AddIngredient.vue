<template>
<div id = "add">
    <div>
      <div v-if="validationFailMsg.length > 0"> <!--DIY Validation Step 4 Add a div that shows the error if there is one. You should style this yourself-->
        Error: {{validationFailMsg}}
      </div>
      <form>
        Ingredient Name:
        <input type="text" class="form-control" v-model="newIngredient.ingredientName" required/>
        <button type="submit" class="btn" v-on:click="createIngredient">Add Ingredient</button>
      </form>
    </div>
  </div>
  
</template>

<script>
import ingredientService from "@/services/IngredientService";


export default {
name: "add-ingredient",
  data() {
    return {
      newIngredient: {
        ingredientName: "",
      },
      validationFailMsg:"",
    };
  },
   methods: {
     checkValidation() {
      this.validationFailMsg = "";
      if (this.newIngredient.ingredientName.length === 0) {
        this.validationFailMsg += "Ingredient name is required\n";
      }
      
      return this.validationFailMsg.length ===0;
    },
    createIngredient() {
      if(this.checkValidation()){
      ingredientService
        .addIngredient(this.newIngredient)
        .then((response) => {
          if (response.status === 200) {
            alert("Ingredient Succesfully Added");
            this.$router.push({ name: 'home' });
            this.newIngredient={}
          }
        })
        .catch((error) => {
          if (error.response) {
            this.errorMsg =
              "Error adding ingredient. Response received was '" +
              error.response.statusText +
              "'.";
          } else if (error.request) {
            this.errorMsg = "Error adding ingredient. Server could not be reached.";
          } else {
            this.errorMsg =
              "Error adding card. Request could not be created.";
          }
        });
      }
    },
  },
}
</script>

<style>

form {
  display: flex;
  flex-direction: column;
  margin: 10px;
}

#add {
  display:flex;
  justify-content: center;

}

form{
  border: black 3px solid;
  padding: 10px;
  border-radius: 10px;

}
</style>