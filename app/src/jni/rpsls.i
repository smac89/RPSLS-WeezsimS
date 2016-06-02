%module rpsls
%{
    /* Includes the header in the wrapper code */
    #include "rpsls.h"
%}

/* Parse the header file to generate wrappers */
%include "std_string.i" // for std::string type-maps
%include "rpsls.h"
