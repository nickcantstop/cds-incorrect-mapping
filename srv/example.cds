service Sample {

    event Foo {
        actionId : Integer64
    }

    event Bar {
        actionId : Integer64
    }

    type payload {
        actionId : Integer64
    }

    action onFooEvent(data: payload)

}
