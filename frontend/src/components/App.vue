<script setup>
  import { ref } from 'vue';

    var showNavbarState = ref(true)
    var changeMainsState = ref(true)
    var showLoginFormState = ref(false)
    var showRegisterFormState = ref(false)

    const mains1 = ref(null)
    const mains2 = ref(null)
    const user = ref(null)

    fetch('http://localhost:8081/api/market-data/v1/main/mains-now')
      .then(response => response.json())
      .then(data => {
        mains1.value = data.slice(0, 3)
        mains2.value = data.slice(3, 6)
        }
      )

    const showNavbar = () => {
      showNavbarState = !showNavbarState

      if (!showNavbarState) {
        document.getElementById('navbar').style.top = "0"
        document.getElementById('switcher').style.top = "55px"
        document.getElementById('down-arr').style.transform = "rotate(180deg)"
      } else {
        document.getElementById('navbar').style.top = "-55px";
        document.getElementById('switcher').style.top = "0"
        document.getElementById('down-arr').style.transform = "rotate(0deg)"
      }
    }

    const showRegisterForm = () => {
      showLoginFormState = false
      //showRegisterFormState = true
    }

</script>

<template>
  <main>
    <div v-if="showRegisterFormState" class="overlay">
    </div>

    <div v-if="showLoginFormState" class="overlay">
      <div class="login-form">
        <div class="close-wrapper">
          <fa class="close-btn-icon" icon="fa-times" @click="showLoginFormState = false"></fa>
        </div>
        <div class="login-input-wrapper">
          <h1>Login to InvestView</h1>
          <input type="text" class="login-input" placeholder="email">
          <input type="password" class="login-input" placeholder="password">
          <div class="register-link-wrapper">
            <a class="register-link" @click="showRegisterForm()">Don't have an account? Register!</a>
          </div>
        </div>
        <button class="login-btn">Login</button>
      </div>
    </div>

    <div class="navbar" id="navbar">
      <ul class="navbar-menu">
        <li><a @click="showLoginFormState = true">Login</a></li>
        <li><a>About project</a></li>
        <li><a>Contact</a></li>
      </ul>
      <h1>InvestView</h1>
    </div>
    <div class="navbar-switch" id="switcher">
      <fa id="down-arr" class="down-arrow" icon="fa-chevron-down" @click="showNavbar()"></fa>
    </div>

    <div class="input-wrapper">
      
      <div class="input-box">
        <fa class="search-icon" icon="search"></fa>
        <input type="search" placeholder="Enter issuer name: GAZP" class="main-input">
      </div>
    </div>
    <div class="mains-wrapper">
      <div class="btn-wrapper">
        <button class="arrow-btn" @click="changeMainsState = !changeMainsState"><fa class="arrow-icon" icon="arrow-left"></fa></button>
      </div>
      <div  class="mains-container">
        <div v-for="issuer in (changeMainsState? mains1 : mains2)" class="main-card">
          <div class="card-half">
            <h2 class="secid">{{ issuer.secId }}</h2>
            <h3 class="fullname">{{ issuer.fullName }}</h3>
          </div>
          <div class="card-half">
            <h2 class="price">{{ issuer.priceNow }}</h2>
          </div>
        </div>
      </div>
      <div class="btn-wrapper">
        <button class="arrow-btn"  @click="changeMainsState = !changeMainsState"><fa class="arrow-icon" icon="arrow-right"></fa></button>
      </div>
    </div>
  </main>
</template>

<style scoped>
  main{
    width: 100vw;
    height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #1A1A1A;
  }

  .overlay {
    position: absolute;
    width: 100vw;
    height: 100vh;
    z-index: 10;
    background-color: rgba(0, 0, 0, 0.77);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .login-form {
    width: 750px;
    height: 450px;
    background-color: #1A1A1A;
    border-radius: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-left: 35px;
    padding-right: 35px;
  }

  .close-wrapper {
    width: 100%;
    height: 75px;
    display: flex;
    justify-content: flex-end;
    background-color: transparent;
    align-items: center;
  }

  .close-btn-icon {
    font-size: xx-large;
    color: #A0A0A0;
    transition: 0.3s;
    cursor: pointer;
  }

  .close-btn-icon:hover {
    color:#ff4949;
  }

  .login-input-wrapper {
    width: 100%;
    height: 60%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
  }

  .login-input-wrapper h1 {
    color: #A0A0A0;
  }

  .login-input-wrapper input {
    background-color: transparent;
    padding-left: 30px;
    width: 60%;
    height: 30px;
    transition: 0.3s;
    outline: none;
    border: 2px solid #A0A0A0;
    border-radius: 5px;
    margin-top: 25px;
    color: #A0A0A0;
  }

  ::-ms-reveal {
    filter: invert(100%);
  }

  .register-link-wrapper {
    width: 65%;
    height: 20px;
    align-items: center;
    border-bottom: 1px solid #42B883;
    display: flex;
    justify-content: center;
    padding-top: 15px;
    padding-bottom: 15px;
  }

  .register-link-wrapper a {
    color: #A0A0A0;
    cursor: pointer;
  }

  .register-link-wrapper a:hover {
    color: #42B883;
  }
  
  .login-btn {
    margin-top: 15px;
    width: 100px;
    height: 40px;
    transition: 0.4s;
    color: #42B883;
    border: 2px solid #42B883;
    border-radius: 5px;
    background-color: transparent;
    cursor: pointer;
  }

  .login-btn:hover {
    background-color: #42B883;
    color: #1A1A1A;
  }

  .login-input-wrapper input:focus {
    border-color: #42B883;
  }

  .login-input-wrapper input:hover {
    border-color: #42B883;
  }
  .navbar {
    top: -55px;
    position: fixed;
    height: 55px;
    width: 100vw;
    transition: 0.4s;
    background-color: #2F2F2F;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .navbar h1 {
    margin-right: 30px;
    color: #A0A0A0;
  }

  .navbar-menu {
    display: flex;
    align-items: center;
    justify-content: space-around;
    height: 100%;
    width: 20%;
  }

  .navbar-menu li {
    list-style: none;
    color: #A0A0A0;
    transition: 0.25s;
    cursor: pointer;
  }

  .navbar-menu li:hover {
    color: white;
  }

  .navbar-switch {
    position: fixed;
    width: 10vw;
    min-width: 70px;
    height: 5vh;
    background-color: #2F2F2F;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 0 0 15px 15px;
    transition: 0.4s;
  }

  .down-arrow {
    color: #A0A0A0;
    font-size: xx-large;
    cursor: pointer;
  }

  .input-wrapper{
    margin-top: 30vh;
    width: 40vw;
    height: 50px;
    border-radius: 20px;
    border: 2px solid #42b883;
    background-color: #2F2F2F;
    min-width: 600px;
  }

  .input-box {
    display: flex;
    widows: 100%;
    height: 100%;
    align-items: center;
    min-height:50px;;
  }

  .main-input{
    width: 100%;
    height: 100%;
    border: none;
    outline: none;
    color: #A0A0A0;
    font-size: large;
    padding-left: 10px;
    background-color: transparent;
  }

  .search-icon {
    color: #A0A0A0;
    margin-left: 10px;   
    margin-bottom: 3px; 
  }

  .mains-wrapper {
    width: 100vw;
    height: 45vh;
    min-height: 300px;
    background-color: transparent;
    margin-top: 30px;
    display: flex;
  }

  .btn-wrapper {
    height: 100%;
    width: 5%;
    background-color: transparent;
    align-items: center;
    justify-content: center;
    display: flex;
  }

  .mains-container {
    height: 100%;
    width: 90%;
    background-color: transparent;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .main-card {
    width: 30%;
    height: 80%;
    background-color: #2F2F2F;
    border-radius: 30px;
    border: 2px solid #42B883;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .card-half {
    width: 90%;
    height: 50%;
    padding-top: 10px;
    display: flex;
    align-items: center;
    justify-content: space-between;

  }

  .card-half + .card-half {
    border-top: 1px solid #42B883;
  }

  .secid {
    font-weight: bold;
    color: #A0A0A0;
  }

  .fullname {
    color: #A0A0A0;
  }

  .price {
    font-weight: bold;
    color: #A0A0A0;
  }

  .arrow-btn {
    background-color: transparent;
    border: 2px solid #42B883;
    border-radius: 100%;
    height: 40px;
    width: 40px;
    transition: 0.4s;
    cursor: pointer;
  }

  .arrow-btn:hover {
    background-color: #42B883;
  }

  .arrow-btn:hover * {
    color: #1A1A1A;
  }

  .arrow-icon {
    color: #42B883;
    font-size: large;
    margin-top: 2px;
    transition: 0.4s;
  }
</style>
