

node() {
    stage("mailer") {
        recipients = "cc:twoj@mail.pl"
        if(env.DEFAULT_RECIPIENTS) {
            recipients = recipients + ",${DEFAULT_RECIPIENTS}"
        }
        emailext(
            to: recipients,
            body: '${DEFAULT_CONTENT}', 
            mimeType: 'text/html',
            subject: '${DEFAULT_SUBJECT}',
            replyTo: '$DEFAULT_REPLYTO'    
        )
    }
}
