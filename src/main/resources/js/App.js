import React, { Component } from 'react';
import {Container} from './components/container';

class App extends Component {
  render() {
    const urlParams = new URLSearchParams(location.search);
    return (
      <Container issueId={urlParams.get("issueId")}/>
    );
  }
}

export default App;
