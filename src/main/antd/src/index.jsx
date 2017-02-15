import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, hashHistory } from 'react-router';

import App from './components/App';
import Header from './components/Header';

ReactDOM.render((
  <Router history={hashHistory}>
    <Route path="/" component={App} />
    <Route path="/index" component={App} />
    <Route path="/header" component={Header} />
  </Router>
), document.getElementById('content'));
