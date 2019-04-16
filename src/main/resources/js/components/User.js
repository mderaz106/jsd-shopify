import React from 'react';

export default class User extends React.PureComponent {

    render() {
        return (
            <h1>{this.props.firstName} {this.props.lastName}</h1>
        );
    }
};
