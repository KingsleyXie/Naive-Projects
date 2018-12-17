#include <unistd.h>
#include <stdio.h>

int main(int argc, char *argv[])
{
    int pid = fork();

    if (pid < 0) printf("Error occured while forking\n");
    else if (pid == 0)
    {
        execv("./lab1/an_ch2_lb", argv);
    }

    return 0;
}
