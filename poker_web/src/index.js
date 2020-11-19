import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import Login from './Login';
import { BrowserRouter, Route } from 'react-router-dom';

ReactDOM.render(
  <BrowserRouter>
    <Route path="/login" component={Login} />
    <Route path="/poker" component={App} />
  </BrowserRouter>,
  document.getElementById('root')
);
