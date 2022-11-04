import React, {useState} from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import logo from './logo.svg';
import './App.css';

import Home from './components/Home';
import Search from './components/Search';
import Checkout from './components/Checkout';

function App() {
  const API_URL = "http://localhost:8084";
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/">
          <Route index element={<Home api_url={API_URL} />} />
          <Route path="search" element={<Search api_url={API_URL} />} />
          <Route path="checkout" element={<Checkout api_url={API_URL} />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
