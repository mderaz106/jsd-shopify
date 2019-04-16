
import React, { Component } from 'react';

import '@atlaskit/css-reset';
import '@atlaskit/reduced-ui-pack';

import Container from './components/Container';

class App extends Component {
  render() {
    const urlParams = new URLSearchParams(location.search);
    const jwtToken = document.getElementsByName('connect-token')[0].getAttribute('content');
    return (
      <Container issueId={urlParams.get("issueId")} jwtToken={jwtToken}/>
    );
  }
}

export default App;
