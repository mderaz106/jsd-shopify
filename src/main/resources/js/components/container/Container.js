import React from 'react';

export default class Container extends React.PureComponent {
    constructor(props) {
        super(props);
        this.state = {
            shopifyData: {},
        };
    }

    componentDidMount() {
        fetch(`/jira/connect/reporter/shopify-details?issueId=${this.props.issueId}`)
            .then(response => {
                this.setState({
                    ...this.state,
                    shopifyData: response.json()
                })
            });
    }

    render() {
        const shopifyData = this.state.shopifyData;
        return (
            <div>
                {JSON.stringify(shopifyData)}
            </div>
        );
    }
}