<script setup>
  import { ref } from 'vue';
    
    class Issuer {
        constructor(secId, fullName, priceNow) {
            this.secId = secId
            this.fullName = fullName
            this.priceNow = priceNow
        }
    }

    const mains = ref([new Issuer('GAZP', 'Газпром', 270.1), new Issuer('TATN', 'Татнефть', 619.3), new Issuer('YNDX', 'Яндекс', 2185)])
    var showNavbarState = ref(true)

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
</script>

<template>
  <main>
    <div class="navbar" id="navbar">
      <ul class="navbar-menu">
        <li><a>Login</a></li>
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
        <button class="arrow-btn"><fa class="arrow-icon" icon="arrow-left"></fa></button>
      </div>
      <div class="mains-container">
        <div v-for="issuer in mains" class="main-card">
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
        <button class="arrow-btn"><fa class="arrow-icon" icon="arrow-right"></fa></button>
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
    border: 2px solid #42B883;
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
  }

  .arrow-icon {
    color: #42B883;
    font-size: large;
    margin-top: 2px;
  }
</style>
