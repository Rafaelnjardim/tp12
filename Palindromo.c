#include <stdio.h>
#include <string.h>
#include <stdbool.h>
bool IsPalindromo(char teste[]){
    bool resp=true;
    int i=strlen(teste)-1;
    int k=i+1;
    for(int j=0;j<k/2;j++){
        if(teste[i]!=teste[j]){
                resp=false;
                j=k/2;
            }
            i--;
        }

    return resp;
}
int main(){
    char teste[100];
    char fim[3]= "FIM";
    gets(teste);
    getchar();
    bool resp;
    resp=IsPalindromo(teste);
    while (strcmp(teste,fim)!=0) {
        bool resp;
        resp=IsPalindromo(teste);
        if (resp==true) {
                printf("SIM");
            }
            else{
                printf("NAO");
            }
        gets(teste);
            getchar();
    }

}

    
