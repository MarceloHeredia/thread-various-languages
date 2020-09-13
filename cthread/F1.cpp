#include <iostream>
#include "pthread.h"
#include <stdlib.h>
#include <unistd.h>

#define N_RACERS 2
#define N_LAPS 10

struct pilot
{
	int totalTime;
	char name[20];
};

void *racer(void *arg)
{

	struct pilot *p = (struct pilot *)arg;

	int time = 0;
	printf("Start pilot %s\n", p->name);

	for (int lap = 1; lap <= N_LAPS; lap++)
	{
		time = rand() % 5;
		sleep(time);
		p->totalTime = p->totalTime + time;

		printf("Pilot %s, Lap %d, time %d, total %d\n", p->name, lap, time, p->totalTime);
	}

	printf("End piloto %s\n", p->name);
}

int main()
{
	pthread_t tp[N_RACERS];

	struct pilot pilots[2];

	pilots[0].totalTime = 0;
	sprintf(pilots[0].name, "Alonso");

	pilots[1].totalTime = 0;
	sprintf(pilots[1].name, "Hamilton");

	printf("Race start\n");

	for (int i = 0; i < N_RACERS; i++)
	{
		pthread_create(&tp[i], NULL, racer, (struct pilot *)&pilots[i]);
	}

	for (int i = 0; i < N_RACERS; i++)
	{
		pthread_join(tp[i], NULL);
	}

	printf("Race end\n");
	for (int i = 0; i < N_RACERS; i++)
	{
		printf("Pilot %s, totalTime %d\n",pilots[i].name, pilots[i].totalTime);
	}

	printf("%s wins!\n", pilots[0].totalTime < pilots[1].totalTime ? pilots[0].name : pilots[1].name);


	return 0;
}