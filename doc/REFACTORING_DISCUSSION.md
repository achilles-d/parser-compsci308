Achilles Dabrowski (ajd66), Achintya Kumar (ask60), Saurav Sanjay (sks61), Abebe Amare(ama100)

##Design Issues

One major design issue that was flagged was poor error handling. This comes from 
our code occasionally printing a stack trace, and also comes from us throwing a new error type
without preserving the original error.

Another major design issue that came up was that we had many private fields that were not
being used. This mostly came up in the front-end since the front-end just implemented
a window factory to create this components. This makes it so that you don't need
the variables anymore referring to each window component. 

Another design issue is that there is a very long method in the command factory class.
It seems to be that there is still a series of conditionals used to perform specific tasks
depending on whether or not we received a certain type of command. We should not be doing that
an instead should maybe use abstractions to try and help remove that conditional chain. 

One other design issue we have is that we have a lot of magic values. This occurs mostly in the front-end
for all of the sizings of the components. 

##Categorize

1. Magic values and excessive unused private field:

    These issues are grouped together as they mostly occur in the front-end and can
easily be fixed by just combing through each class in the front-end. This is very important
as it will help us avoid blatant code smells, like magic values. 

2. Error Handling Issues:

    These issues are grouped together as they all relate to error handling. Fixing this is important as
well since we need to ensure that we don't lose any info when errors are thrown. 

3. Longest Method:

    While at first longest method doesn't seem to be our biggest issue to tackle, 
    the reason the method is so long is because of a series of conditionals. For our parser,
    we need to try and avoid checking if it is a certain command type and performing
    corresponding actions. 


##Design Changes

The magic value and excessive private field issues can be fixed relatively simply together. This requires just going through
the front-end and changing any magic values to constants, and also removing any private
fields that are no longer needed due to using the factory pattern. 

The error handling issues can be fixed by making another constructor for our custom
errors that can take the original error as well, so that information is preserved. 

The longest method issue is probably the one that will take a little longer to fix. It will require us
to think about how we can use abstractions to make it so that we don't need to have many conditionals
to check what kind of command is being used. 

